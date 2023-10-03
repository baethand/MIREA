#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <cstring>
#include <ctime>
#include <map>
#include <chrono>

using namespace std;
using namespace chrono;

//Структура записи
struct Record {
    int registrationNumber;
    string companyName;
};

// Функция для проверки уникальности номера
bool uniqueChecker(const vector<Record*>& records, int key) {
    for (const Record* record : records) {
        if (record->registrationNumber == key)
            return false;
    }
    return true;
}

// Функция для создания файла с записями 
map<int, int>  createFile(int N) {
    srand(time(NULL));

    vector<Record*> records;

    string companies[] = { "CompanyA", "CompanyB", "CompanyC", "CompanyD", "CompanyE" };

    // Создание уникального номера
    for (int i = 0; i < N; i++) {
        Record* newRecord = new Record();
        int newKey = 100000 + rand() % 900000; // Генерация случайного 6-значного номера

        //Проверка на уникальность уже созданных записей
        while (!uniqueChecker(records, newKey))
            newKey = 100000 + rand() % 900000;

        newRecord->registrationNumber = newKey;
        newRecord->companyName = companies[rand() % 5];
        records.push_back(newRecord);
    }
    map<int, int> codeToRecordMap;

    //Запись данных в бинарный файл
    ofstream fout("test.bin", ios_base::binary);
    for (int i = 0; i < N; i++) {
        size_t before = fout.tellp(); //сохраняем смещение

        //Запись читательского кода
        fout.write((char*)&records[i]->registrationNumber, sizeof(int));
        cout << records[i]->registrationNumber << " : " << records[i]->companyName << endl;

        //Запись в файл
        const char* companyData = records[i]->companyName.c_str();
        int companyLength = records[i]->companyName.size();
        fout.write(companyData, companyLength + 1);

        codeToRecordMap[records[i]->registrationNumber] = before;
    }

    fout.close();
    for (Record* record : records)
        delete record;

    cout << "Файл на ( " << N << " ) записей создан" << endl;
    return codeToRecordMap;
}

// Функция для поиска записи линейным поиском
void linearFindRecord(int num, int code_to_find) {
    // Открытие бинарного файла для чтения
    ifstream fin("test.bin", ios_base::binary);


    for (int i = 0; i < num; i++) {
        Record tmp;

        // Чтение номера 
        fin.read((char*)&tmp.registrationNumber, sizeof(int));

        string companyData;
        char ch;
        fin.get(ch);
        while (ch != '\0') {
            companyData += ch;
            fin.get(ch);
        }
        tmp.companyName = companyData;

        if (tmp.registrationNumber == code_to_find) {
            cout << "Запись найдена: " << endl;
            cout << "Код: " << tmp.registrationNumber << endl;
            cout << "Название компании: " << tmp.companyName << endl;
            break;
        }

    }
    // Закрытие файла
    fin.close();
}

Record reading(int offset) {
    ifstream fin("test.bin", ios_base::binary);
    fin.seekg(offset); // Смещение в файле
    Record tmp;
    // Чтение данных из файла
    fin.read((char*)&tmp.registrationNumber, sizeof(int));
    string companyName;
    char ch;
    fin.get(ch);
    while (ch != '\0') {
        companyName += ch;
        fin.get(ch);
    }
    tmp.companyName = companyName;

    fin.close();
    return tmp;
}

void interpolationFindRecord(const map<int, int>& codeToRecordMap, int codeToFind) {
    map<int, int>::const_iterator iterator;

    int n = codeToRecordMap.size();
    int left = 0;
    int right = n - 1;

    while (left <= right && codeToFind >= codeToRecordMap.begin()->first && codeToFind <= codeToRecordMap.rbegin()->first) {
        iterator = codeToRecordMap.begin();

        // Интерполяционная формула для приближенной позиции
        int pos = left + ((codeToFind - iterator->first) * (right - left)) / (codeToRecordMap.rbegin()->first - iterator->first);

        advance(iterator, pos);

        if (iterator->first == codeToFind) {
            Record tmp = reading(iterator->second);
            cout << "Запись найдена: " << endl;
            cout << "Код: " << tmp.registrationNumber << endl;
            cout << "Название компании: " << tmp.companyName << endl;
            return;
        }
        else if (iterator->first < codeToFind) {
            left = pos + 1; // Искать в правой половине
        }
        else {
            right = pos - 1; // Искать в левой половине
        }
    }
    cout << "Запись не найдена!";
}


int main() {
    setlocale(LC_ALL, "ru");
    //cout << " == " << sizeof(Record) << endl; //Вывод кол-ва байт занимаемых одной записью
    int N = 100;
    map<int, int> codeToRecordMap = createFile(N); // Создание файла и таблицы
    

    int code_to_find;
    cout << "Введите код для нахождения: ";
    cin >> code_to_find;

    //linearFindRecord(N, code_to_find);     // Поиск записи по номеру

    // Замер выполнения метода

    auto start_time = high_resolution_clock::now();
    //linearFindRecord(N, code_to_find);
    interpolationFindRecord(codeToRecordMap, code_to_find);
    auto end_time = high_resolution_clock::now();

    auto duration = duration_cast<milliseconds>(end_time - start_time);

    cout << "Время выполнения функции findRecord: " << duration.count() << " милисекунд" << endl;

    return 0;
}
