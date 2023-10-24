#include <unordered_map>// ������������ ����������� (��������� ���: ����-��������)
#include <iostream>
#include <fstream>
#include <format>
#include <string>
#include <vector>
#include <list>
#include <set>

using namespace std;


//��������� ������ � ��������: ����� ������, �������, ���, ��������, ��� �����������, ���� ��������� ��������, ��� �����.
struct InsuranceNote {
    unsigned long int number = 0;
    string surname;
    string first_name;
    string mid_name;
    unsigned long int disease�ode = 0;
    string date;
    unsigned long int doctor�ode = 0;
};

//��������� ������ � ���-�������
struct HashTableEntry {
    unsigned long int key;
    int hash;
};

//����� ������, ��������� �� 8 �����
unsigned long int Randomizer() {
    unsigned long int min = 10000000;
    unsigned long int max = 99999999;
    return min + rand() % (max - min + 1);
}

//���-������� ������� �������
int HashFun(unsigned long int key, int tableSize) {
    return key % tableSize;
}

//������� ����� � �������
void Insert(unordered_map<int, list<HashTableEntry>>& hashTable, unsigned long int key, int tableSize) {
    HashTableEntry entry;
    entry.key = key;
    entry.hash = HashFun(key, tableSize);
    hashTable[entry.hash].push_back(entry);
}

//�������� ����� �� �������
void Remove(unordered_map<int, list<HashTableEntry>>& hashTable, unsigned long int key, int tableSize) {
    int hash = HashFun(key, tableSize);
    auto& entries = hashTable[hash];// ��������� ������ �� ������ (���� �������) 
    for (auto it = entries.begin(); it != entries.end(); ++it) {
        if (it->key == key) {
            entries.erase(it);
            break;// ����� �� ����� ����� �������� �����
        }
    }
}

//����� ����� � �������
bool Find(const unordered_map<int, list<HashTableEntry>>& hashTable, unsigned long int key, int tableSize) {
    int hash = HashFun(key, tableSize);
    auto& entries = hashTable.at(hash); // ��������� ������ �� ������ (���� �������) 
    for (const HashTableEntry& entry : entries) {
        if (entry.key == key) {
            return true;// ���� ������
        }
    }
    return false;// ���� �� ������
}

//������������� �������
void Rehash(unordered_map<int, list<HashTableEntry>>& hashTable, int newTableSize) {
    unordered_map<int, list<HashTableEntry>> newHashTable(newTableSize);
    for (const auto& entry : hashTable) { // ������ �� ���� ��������� ���-�������
        for (const HashTableEntry& hashEntry : entry.second) { // ������ �� ���� ������� ����
            int newHash = HashFun(hashEntry.key, newTableSize);
            newHashTable[newHash].push_back(hashEntry);
        }
    }
    hashTable = newHashTable;
}

//����������� �������� �����
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
    //��������� �������� ������� �� ��������� ���������
    int newSize = currentSize * 2;

    //�������� �� ������� �����, ����� ��������� ����������� ��������
    while (!IsPrime(newSize)) {
        newSize++;
    }
    return newSize;
}

void Output(unordered_map<int, list<HashTableEntry>> hashTable) {
    //����� ������ ���-���� � �����
    cout << endl << "����� ���������" << endl;
    for (const auto& entry : hashTable) {
        int hash = entry.first;// ��������� ���-����
        const list<HashTableEntry>& entries = entry.second;// ��������� ������ �������
        for (const HashTableEntry& hashEntry : entries) {
            unsigned long int key = hashEntry.key;// ��������� �����
            printf("   ����: %d   ���: %d\n", key, hash);
        }
    }
}

int main() {
    // ������� 3.2

    setlocale(LC_ALL, "ru");
    srand(static_cast<unsigned int>(time(nullptr)));// ������������� ����������

    ofstream textFile("textFile.txt");// ��������� ����
    if (!textFile) {
        cout << "��������� ���� �� ������� ������� ��� ������ +_+" << endl;
        return 1;
    }
    cout << "��������� ���� ������� ������ ��� ������ ^_^" << endl << endl;

    // ��������� ���� ��� ������ � �������� ������ (���� - ios::binary)
    ofstream binaryFileOut("binaryFile.bin", ios::binary);// �������� ����
    if (!binaryFileOut) {
        cerr << "�������� ���� �� ������� ������� ��� ������ +_+" << endl;
        return 1;
    }
    cout << "�������� ���� ������� ������ ��� ������ ^_^" << endl << endl;

    int quant = 14;// ���������� �������
    set<int> unique;

    InsuranceNote insNote;
    for (int i = 0; i < quant; ++i) {
        do {
            insNote.number = Randomizer();
        } while (unique.count(insNote.number) > 0);

        unique.insert(insNote.number);
        insNote.surname = "�������_" + to_string(i);
        insNote.first_name = "���_" + to_string(i);
        insNote.mid_name = "��������_" + to_string(i);
        insNote.date = (to_string(Randomizer() % 32+1) + "." + to_string(Randomizer() % 13+1) + "." + to_string(Randomizer() % 23 + 2000));
        insNote.disease�ode = Randomizer() % 10 + 100;
        insNote.doctor�ode = Randomizer() % 101000 + 100000;
        
        textFile << insNote.number << ' ' << insNote.surname << ' ' << insNote.first_name << ' ' << insNote.mid_name << ' ' << insNote.date << ' ' << insNote.disease�ode << ' ' << insNote.doctor�ode << '\n';

        binaryFileOut.write(reinterpret_cast<const char*>(&insNote), sizeof(insNote));
    }
    cout << "��������� ���������� � ��������� ����� ��������� ^_^" << endl;

    size_t bytes = sizeof(InsuranceNote);
    cout << "������ ������ � ������: " << bytes << endl;
    cout << "���������� �������: " << quant << endl << endl;

    textFile.close();
    binaryFileOut.close();

    const int tableSize = 7;// ������ ���-�������
    unordered_map<int, list<HashTableEntry>> hashTable;// �������� ���-������� � ���������

    // ��������� �������� � ���-�������
    ifstream binaryFileIn("binaryFile.bin", ios::binary);
    if (!binaryFileIn) {
        cout << "�������� ���� �� ������� ������� ��� ������ +_+" << endl;
        return 1;
    }
    cout << "�������� ���� ������� ������ ��� ������ ^_^" << endl << endl;

    cout << "   �������� ���-�������� >.<" << endl << endl;
    while (binaryFileIn.read(reinterpret_cast<char*>(&insNote), sizeof(insNote))) {
        // �������� ������ ��� ���-�������
        HashTableEntry entry;
        entry.key = insNote.number;
        entry.hash = HashFun(entry.key, tableSize);// ���������� ���-���� ��� �����

        hashTable[entry.hash].push_back(entry);// ���������� ������ � ������� ���-�������

        printf("   ����: %d   ���: %d\n", entry.key, entry.hash);
    }

    binaryFileIn.close();

    // ������ ����������
    double loadFactor = static_cast<double>(hashTable.size()) / tableSize;

    bool flag = true;
    while (flag) {
        int choice;
        cout << endl << " �������� �������� �� ����������:\n [1] - ������� �����\n [2] - ����� �����\n [3] - �������� �����\n [4] - ����� �� ������������\n";
        cin >> choice;
        switch (choice) {
        case 1:
            unsigned long int insert;
            cout << "������� ����� ������, ������� �� ������� �������� (8 ����): ";
            cin >> insert;
            Insert(hashTable, insert, tableSize);
            Output(hashTable);
            if (loadFactor > 0.8)
            {
                int newTableSize = CalculateSize(tableSize); // ����������� ������ ������� �������
                Rehash(hashTable, newTableSize);
            }
            break;
        case 2:
            unsigned long int found;
            cout << "������� ����� ������, ������� �� ������� ����� (8 ����): ";
            cin >> found;
            if (Find(hashTable, found, tableSize))
                cout << "��������� ����� ������!" << endl;
            else
                cout << "��������� ����� �� ������!" << endl;
            break;
        case 3:
            unsigned long int remove;
            cout << "������� ����� ������, ������� �� ������� ������� (8 ����): ";
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
