#include <iostream>
#include <fstream>
#include <ctime>
#include <vector>
#include <string>

using namespace std;

const string companies[] = { "CompanyA", "CompanyB", "CompanyC", "CompanyD" };
const char fileName[] = "E:\\C++\\insurance_records.dat";

struct InsuranceRecord {
    int registrationNumber;
    string companyName;
};

struct IndexEntry {
    int key;
    streampos offset;
};

InsuranceRecord generateRecord() {
    InsuranceRecord record;
    record.registrationNumber = rand() % 1000000; // Генерируем шестизначный номер
    record.companyName = companies[rand() % 4];
    return record;
}

void createBinaryFile(const char* fileName, int numRecords) {
    ofstream file(fileName, ios::binary | ios::out);
    if (!file) {
        cerr << "Ошибка при создании файла." << endl;
        return;
    }

    srand(time(nullptr));

    for (int i = 0; i < numRecords; i++) {
        InsuranceRecord record = generateRecord();
        file.write(reinterpret_cast<const char*>(&record), sizeof(InsuranceRecord));
    }

    file.close();
}

void buildIndex(const char* filename, vector<IndexEntry>& index) {
    ifstream file(filename, ios::binary | ios::in);
    if (!file.is_open()) {
        cerr << "Ошибка открытия файла для построения индекса." << endl;
        return;
    }

    InsuranceRecord record;
    streampos position = 0;
    while (file.read(reinterpret_cast<char*>(&record), sizeof(InsuranceRecord))) {
        IndexEntry entry;
        entry.key = record.registrationNumber;
        entry.offset = position;
        index.push_back(entry);
        position = file.tellg();
    }

    file.close();
}

bool indexedSearch(const char* filename, const vector<IndexEntry>& index, int key, InsuranceRecord& result) {
    for (const IndexEntry& entry : index) {
        if (entry.key == key) {
            ifstream file(filename, ios::binary | ios::in);
            if (!file.is_open()) {
                cerr << "Ошибка открытия файла для чтения." << endl;
                return false;
            }

            file.seekg(entry.offset);
            file.read(reinterpret_cast<char*>(&result), sizeof(InsuranceRecord));
            file.close();
            return true;
        }
    }

    return false;
}

bool searchBinaryFile(const char* fileName, int key, InsuranceRecord& foundRecord) {
    ifstream file(fileName, ios::binary | ios::in);
    if (!file) {
        cerr << "Ошибка при открытии файла." << endl;
        return false;
    }

    while (file.read(reinterpret_cast<char*>(&foundRecord), sizeof(InsuranceRecord))) {
        if (foundRecord.registrationNumber == key) {
            file.close();
            return true;
        }
    }

    file.close();
    return false;
}


void zad1() {
    createBinaryFile(fileName, 10);
    cout << "Запись прошла успешно!";
}

void zad2() {
    int numRecords[] = {100};

    for (int numRecord : numRecords) {
        createBinaryFile(fileName, numRecord);
        cout << "Запись прошла успешно в файл " << numRecord << " прошла успешно!" << endl;

        InsuranceRecord result;
        int searchKey = rand() % 1000000; // Генерируем ключ для поиска
        clock_t linearStart = clock();
        bool resBinaryFile = searchBinaryFile(fileName, searchKey, result);
        clock_t linearEnd = clock();

        if (resBinaryFile) {
            cout << "Найдена запись по ключу " << searchKey << ": " << result.companyName << endl;
        }
        else {
            cout << "Запись с ключом " << searchKey << " не найдена." << endl;
        }

        cout << "Время выполнения линейного поиска ( "<< numRecord << " ): " << double(linearEnd - linearStart) / CLOCKS_PER_SEC << " секунд." << endl;
    }
}

void zad3() {
    remove(fileName);
    createBinaryFile(fileName, 100);

    vector<IndexEntry> index;
    buildIndex(fileName, index);

    InsuranceRecord result;
    int searchKey = rand() % 1000000; // Генерируем ключ для поиска
    clock_t indexedStart = clock();
    bool indexedResult = indexedSearch(fileName, index, searchKey, result);
    clock_t indexedEnd = clock();

    if (indexedResult) {
        cout << "Найдена запись по ключу " << searchKey << ": " << result.companyName << endl;
    }
    else {
        cout << "Запись с ключом " << searchKey << " не найдена." << endl;
    }

    cout << "Время выполнения поиска с использованием индекса: " << double(indexedEnd - indexedStart) / CLOCKS_PER_SEC << " секунд." << endl;
}

int main() {
    setlocale(LC_ALL, "RUS");
    srand(static_cast<unsigned int>(time(nullptr)));

    //zad1();
    //zad2();
    zad3();

    return 0;
}
