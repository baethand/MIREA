#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <ctime>
#include <vector>

// Структура записи
struct InsuranceRecord {
    int registrationNumber; // Регистрационный номер (ключ)
    std::string insuranceCompany; // Название страховой компании

    // Конструктор
    InsuranceRecord() {
        registrationNumber = rand() % 900000 + 100000; // Генерируем шестизначное число
        insuranceCompany = "Страховая компания #" + std::to_string(rand() % 1000);
    }
};

// Функция создания и заполнения двоичного файла
void createBinaryFile(const std::string& filename, int numRecords) {
    std::ofstream outFile(filename, std::ios::binary);
    if (!outFile) {
        std::cerr << "Ошибка открытия файла для записи." << std::endl;
        return;
    }

    std::vector<InsuranceRecord> records;

    // Заполняем записи случайными данными
    for (int i = 0; i < numRecords; ++i) {
        InsuranceRecord record;
        records.push_back(record);
    }

    // Записываем данные в файл
    outFile.write(reinterpret_cast<char*>(records.data()), sizeof(InsuranceRecord) * numRecords);

    outFile.close();
    std::cout << "Двоичный файл успешно создан." << std::endl;
}

int main() {
    setlocale(LC_ALL, "RUS");
    const std::string filename = "insurance_records.bin";
    const int numRecords = 20; // Количество записей

    // Инициализируем генератор случайных чисел
    srand(static_cast<unsigned>(time(nullptr)));

    createBinaryFile(filename, numRecords);

    return 0;
}
