#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <ctime>
#include <vector>

// ��������� ������
struct InsuranceRecord {
    int registrationNumber; // ��������������� ����� (����)
    std::string insuranceCompany; // �������� ��������� ��������

    // �����������
    InsuranceRecord() {
        registrationNumber = rand() % 900000 + 100000; // ���������� ������������ �����
        insuranceCompany = "��������� �������� #" + std::to_string(rand() % 1000);
    }
};

// ������� �������� � ���������� ��������� �����
void createBinaryFile(const std::string& filename, int numRecords) {
    std::ofstream outFile(filename, std::ios::binary);
    if (!outFile) {
        std::cerr << "������ �������� ����� ��� ������." << std::endl;
        return;
    }

    std::vector<InsuranceRecord> records;

    // ��������� ������ ���������� �������
    for (int i = 0; i < numRecords; ++i) {
        InsuranceRecord record;
        records.push_back(record);
    }

    // ���������� ������ � ����
    outFile.write(reinterpret_cast<char*>(records.data()), sizeof(InsuranceRecord) * numRecords);

    outFile.close();
    std::cout << "�������� ���� ������� ������." << std::endl;
}

int main() {
    setlocale(LC_ALL, "RUS");
    const std::string filename = "insurance_records.bin";
    const int numRecords = 20; // ���������� �������

    // �������������� ��������� ��������� �����
    srand(static_cast<unsigned>(time(nullptr)));

    createBinaryFile(filename, numRecords);

    return 0;
}
