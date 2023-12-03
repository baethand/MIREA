#include <unordered_map>// Непорядковое отображение (коллекция пар: ключ-значение)
#include <iostream>
#include <fstream>
#include <format>
#include <string>
#include <vector>
#include <list>
#include <set>

using namespace std;


//Структура записи о пациенте: номер полиса, фамилия, имя, отчество, код заболевания, дата установки диагноза, код врача.
struct InsuranceNote {
    unsigned long int number = 0;
    string surname;
    string first_name;
    string mid_name;
    unsigned long int diseaseСode = 0;
    string date;
    unsigned long int doctorСode = 0;
};

//Структура записи в хеш-таблице
struct HashTableEntry {
    unsigned long int key;
    int hash;
};

//Номер полиса, состоящий из 8 чисел
unsigned long int Randomizer() {
    unsigned long int min = 10000000;
    unsigned long int max = 99999999;
    return min + rand() % (max - min + 1);
}

//Хеш-функция методом деления
int HashFun(unsigned long int key, int tableSize) {
    return key % tableSize;
}

//Вставка ключа в таблицу
void Insert(unordered_map<int, list<HashTableEntry>>& hashTable, unsigned long int key, int tableSize) {
    HashTableEntry entry;
    entry.key = key;
    entry.hash = HashFun(key, tableSize);
    hashTable[entry.hash].push_back(entry);
}

//Удаление ключа из таблицы
void Remove(unordered_map<int, list<HashTableEntry>>& hashTable, unsigned long int key, int tableSize) {
    int hash = HashFun(key, tableSize);
    auto& entries = hashTable[hash];// Получение ссылки на список (цепь записей) 
    for (auto it = entries.begin(); it != entries.end(); ++it) {
        if (it->key == key) {
            entries.erase(it);
            break;// Выход из цикла после удаления ключа
        }
    }
}

//Поиск ключа в таблице
bool Find(const unordered_map<int, list<HashTableEntry>>& hashTable, unsigned long int key, int tableSize) {
    int hash = HashFun(key, tableSize);
    auto& entries = hashTable.at(hash); // Получение ссылки на список (цепь записей) 
    for (const HashTableEntry& entry : entries) {
        if (entry.key == key) {
            return true;// Ключ найден
        }
    }
    return false;// Ключ не найден
}

//Рехеширование таблицы
void Rehash(unordered_map<int, list<HashTableEntry>>& hashTable, int newTableSize) {
    unordered_map<int, list<HashTableEntry>> newHashTable(newTableSize);
    for (const auto& entry : hashTable) { // Проход по всем элементам хеш-таблицы
        for (const HashTableEntry& hashEntry : entry.second) { // Проход по всем записям хеша
            int newHash = HashFun(hashEntry.key, newTableSize);
            newHashTable[newHash].push_back(hashEntry);
        }
    }
    hashTable = newHashTable;
}

//Определение простого числа
bool IsPrime(int n) {
    if (n <= 1) {
        return false;
    }
    if (n <= 3) {
        return true;
    }
    if (n % 2 == 0 || n % 3 == 0) {
        return false;
    }
    for (int i = 5; i * i <= n; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0) {
            return false;
        }
    }
    return true;
}

int CalculateSize(int currentSize) {
    //Умножение текущего размера на некоторый множитель
    int newSize = currentSize * 2;

    //Проверка на простое число, чтобы уменьшить вероятность коллизий
    while (!IsPrime(newSize)) {
        newSize++;
    }
    return newSize;
}

void Output(unordered_map<int, list<HashTableEntry>> hashTable) {
    //Вывод списка хеш-кода и ключа
    cout << endl << "После изменения" << endl;
    for (const auto& entry : hashTable) {
        int hash = entry.first;// Получение хеш-кода
        const list<HashTableEntry>& entries = entry.second;// Получение списка записей
        for (const HashTableEntry& hashEntry : entries) {
            unsigned long int key = hashEntry.key;// Получение ключа
            printf("   Ключ: %d   Хеш: %d\n", key, hash);
        }
    }
}

int main() {
    // ЗАДАНИЕ 3.2

    setlocale(LC_ALL, "ru");
    srand(static_cast<unsigned int>(time(nullptr)));// Инициализация генератора

    ofstream textFile("textFile.txt");// Текстовый файл
    if (!textFile) {
        cout << "Текстовый файл не удалось открыть для записи" << endl;
        return 1;
    }
    cout << "Текстовый файл успешно открыт для записи" << endl << endl;

    // Открываем файл для записи в бинарном режиме (флаг - ios::binary)
    ofstream binaryFileOut("binaryFile.bin", ios::binary);// Бинарный файл
    if (!binaryFileOut) {
        cerr << "Бинарный файл не удалось открыть для записи" << endl;
        return 1;
    }
    cout << "Бинарный файл успешно открыт для записи" << endl << endl;

    int quant = 7;// Количество записей
    set<int> unique;

    InsuranceNote insNote;
    for (int i = 0; i < quant; ++i) {
        do {
            insNote.number = Randomizer();
        } while (unique.count(insNote.number) > 0);

        unique.insert(insNote.number);
        insNote.surname = "Фамилия_" + to_string(i);
        insNote.first_name = "Имя_" + to_string(i);
        insNote.mid_name = "Отчество_" + to_string(i);
        insNote.date = (to_string(Randomizer() % 32 + 1) + "." + to_string(Randomizer() % 13 + 1) + "." + to_string(Randomizer() % 23 + 2000));
        insNote.diseaseСode = Randomizer() % 10 + 100;
        insNote.doctorСode = Randomizer() % 101000 + 100000;

        textFile << insNote.number << ' ' << insNote.surname << ' ' << insNote.first_name << ' ' << insNote.mid_name << ' ' << insNote.date << ' ' << insNote.diseaseСode << ' ' << insNote.doctorСode << '\n';

        binaryFileOut.write(reinterpret_cast<const char*>(&insNote), sizeof(insNote));
    }
    cout << "Генерация текстового и бинарного файла завершена" << endl;

    size_t bytes = sizeof(InsuranceNote);
    cout << "Размер записи в байтах: " << bytes << endl;
    cout << "Количество записей: " << quant << endl << endl;

    textFile.close();
    binaryFileOut.close();

    const int tableSize = 7;// Размер хеш-таблицы
    unordered_map<int, list<HashTableEntry>> hashTable;// Создание хеш-таблицы с цепочками

    // Добавляем элементы в хеш-таблицу
    ifstream binaryFileIn("binaryFile.bin", ios::binary);
    if (!binaryFileIn) {
        cout << "Бинарный файл не удалось открыть для чтения" << endl;
        return 1;
    }
    cout << "Бинарный файл успешно открыт для чтения" << endl << endl;

    cout << "   Проверка хеш-индексов" << endl << endl;
    while (binaryFileIn.read(reinterpret_cast<char*>(&insNote), sizeof(insNote))) {
        // Создание записи для хеш-таблицы
        HashTableEntry entry;
        entry.key = insNote.number;
        entry.hash = HashFun(entry.key, tableSize);// Вычисление хеш-кода для ключа

        hashTable[entry.hash].push_back(entry);// Добавление записи в цепочку хеш-таблицы

        printf("   Ключ: %d   Хеш: %d\n", entry.key, entry.hash);
    }

    binaryFileIn.close();

    // Фактор заполнения
    double loadFactor = static_cast<double>(hashTable.size()) / tableSize;

    bool flag = true;
    while (flag) {
        int choice;
        cout << endl << " Выберите действие со структурой:\n [1] - вставка ключа\n [2] - поиск ключа\n [3] - удаление ключа\n [4] - выход из тестирования\n";
        cin >> choice;
        switch (choice) {
        case 1:
            unsigned long int insert;
            cout << "Введите номер полиса, который вы желаете добавить (8 цифр): ";
            cin >> insert;
            Insert(hashTable, insert, tableSize);
            Output(hashTable);
            if (loadFactor > 0.8)
            {
                int newTableSize = CalculateSize(tableSize); // Определение нового размера таблицы
                Rehash(hashTable, newTableSize);
            }
            break;
        case 2:
            unsigned long int found;
            cout << "Введите номер полиса, который вы желаете найти (8 цифр): ";
            cin >> found;
            if (Find(hashTable, found, tableSize))
                cout << "Страховой полис найден!" << endl;
            else
                cout << "Страховой полис не найден!" << endl;
            break;
        case 3:
            unsigned long int remove;
            cout << "Введите номер полиса, который вы желаете удалить (8 цифр): ";
            cin >> remove;
            Remove(hashTable, remove, tableSize);
            Output(hashTable);
            break;
        case 4:
            flag = false;
            break;
        default:
            break;
        }
    }
    return 0;
}

