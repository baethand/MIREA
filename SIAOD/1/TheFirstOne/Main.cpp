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

//��������� ������
struct Record {
    int registrationNumber;
    string companyName;
};

// ������� ��� �������� ������������ ������
bool uniqueChecker(const vector<Record*>& records, int key) {
    for (const Record* record : records) {
        if (record->registrationNumber == key)
            return false;
    }
    return true;
}

// ������� ��� �������� ����� � �������� 
map<int, int>  createFile(int N) {
    srand(time(NULL));

    vector<Record*> records;

    string companies[] = { "CompanyA", "CompanyB", "CompanyC", "CompanyD", "CompanyE" };

    // �������� ����������� ������
    for (int i = 0; i < N; i++) {
        Record* newRecord = new Record();
        int newKey = 100000 + rand() % 900000; // ��������� ���������� 6-�������� ������

        //�������� �� ������������ ��� ��������� �������
        while (!uniqueChecker(records, newKey))
            newKey = 100000 + rand() % 900000;

        newRecord->registrationNumber = newKey;
        newRecord->companyName = companies[rand() % 5];
        records.push_back(newRecord);
    }
    map<int, int> codeToRecordMap;

    //������ ������ � �������� ����
    ofstream fout("test.bin", ios_base::binary);
    for (int i = 0; i < N; i++) {
        size_t before = fout.tellp(); //��������� ��������

        //������ ������������� ����
        fout.write((char*)&records[i]->registrationNumber, sizeof(int));
        cout << records[i]->registrationNumber << " : " << records[i]->companyName << endl;

        //������ � ����
        const char* companyData = records[i]->companyName.c_str();
        int companyLength = records[i]->companyName.size();
        fout.write(companyData, companyLength + 1);

        codeToRecordMap[records[i]->registrationNumber] = before;
    }

    fout.close();
    for (Record* record : records)
        delete record;

    cout << "���� �� ( " << N << " ) ������� ������" << endl;
    return codeToRecordMap;
}

// ������� ��� ������ ������ �������� �������
void linearFindRecord(int num, int code_to_find) {
    // �������� ��������� ����� ��� ������
    ifstream fin("test.bin", ios_base::binary);


    for (int i = 0; i < num; i++) {
        Record tmp;

        // ������ ������ 
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
            cout << "������ �������: " << endl;
            cout << "���: " << tmp.registrationNumber << endl;
            cout << "�������� ��������: " << tmp.companyName << endl;
            break;
        }

    }
    // �������� �����
    fin.close();
}

Record reading(int offset) {
    ifstream fin("test.bin", ios_base::binary);
    fin.seekg(offset); // �������� � �����
    Record tmp;
    // ������ ������ �� �����
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

        // ���������������� ������� ��� ������������ �������
        int pos = left + ((codeToFind - iterator->first) * (right - left)) / (codeToRecordMap.rbegin()->first - iterator->first);

        advance(iterator, pos);

        if (iterator->first == codeToFind) {
            Record tmp = reading(iterator->second);
            cout << "������ �������: " << endl;
            cout << "���: " << tmp.registrationNumber << endl;
            cout << "�������� ��������: " << tmp.companyName << endl;
            return;
        }
        else if (iterator->first < codeToFind) {
            left = pos + 1; // ������ � ������ ��������
        }
        else {
            right = pos - 1; // ������ � ����� ��������
        }
    }
    cout << "������ �� �������!";
}


int main() {
    setlocale(LC_ALL, "ru");
    //cout << " == " << sizeof(Record) << endl; //����� ���-�� ���� ���������� ����� �������
    int N = 100;
    map<int, int> codeToRecordMap = createFile(N); // �������� ����� � �������
    

    int code_to_find;
    cout << "������� ��� ��� ����������: ";
    cin >> code_to_find;

    //linearFindRecord(N, code_to_find);     // ����� ������ �� ������

    // ����� ���������� ������

    auto start_time = high_resolution_clock::now();
    //linearFindRecord(N, code_to_find);
    interpolationFindRecord(codeToRecordMap, code_to_find);
    auto end_time = high_resolution_clock::now();

    auto duration = duration_cast<milliseconds>(end_time - start_time);

    cout << "����� ���������� ������� findRecord: " << duration.count() << " ����������" << endl;

    return 0;
}
