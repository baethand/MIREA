#include <iostream>
#include <bitset>
#include <locale>
#include "prak2.cpp"
#include <vector>

using namespace std;

void printArray(const std::vector<unsigned char>& arr);
void bitwiseSort(std::vector<unsigned char>& arr);
void prak2();

void prog5();
void prog4();
void prog3();
void prog2();
void prog1();
int obnulitBity(int value, int bit1, int bit2, int bit3);

int main() {
    setlocale(LC_ALL, "Russian");
    //prog1();
    //prog2();
    //prog3();
    //prog4();
    //prog5();
    prak2();
	return 0;
}

void prog1() {
	int x = 0xABCD;
	int maska = 0xF000;
	int a = x | maska;
	bitset<16> binaryValue(x);
	bitset<16> binuryMask(maska);
	bitset<16> binuryRes(a);
	cout << binaryValue << endl << binuryMask << endl
		<< "�������� ���������� � �������� �������: " << binuryRes;
}

void prog2() {
    int value;
    std::cout << "������� ��������: ";
    std::cin >> value;

    int bit1 = 9;  // 9-�� ��� (������ � 0).
    int bit2 = 11; // 11-�� ��� (������ � 0).
    int bit3 = 3;  // 3-�� ��� (������ � 0).

    int result = obnulitBity(value, bit1, bit2, bit3);

    std::cout << "���������: " << result << std::endl;
}

void prog3() {
    int number; // ��������� ���������� ������ ����
    int multiplier = 128; // ������ ���������

    std::cout << "������� ����� �����: ";
    std::cin >> number; // ������ ����� � ����������

    // �������� �������� ���������� �� ��������� � ������� ����������� ��������
    number = number << 7; // 128 � �������� ������� ����� 10000000, ������� �� �������� ���� �� 7 ������� �����

    std::cout << "��������� ���������: " << number << std::endl;
}

void prog4() {
    int number; // ��������� ���������� ������ ����

    std::cout << "������� ����� �����: ";
    std::cin >> number; // ������ ����� � ����������

    int divisor = 512; // ������ ��������

    // ������� �������� ���������� �� �������� � ������� ����������� ��������
    number = number >> 9; // 512 � �������� ������� ����� 1000000000, ������� �� �������� ���� �� 9 ������� ������

    std::cout << "��������� �������: " << number << std::endl;
}

void prog5() {
    int number; // ��������� ���������� ������ ����
    int bitPosition; // ����� ����, ������� ����� �������� (������� � 0)
    int maskChoice; // ����� �����: 1 - ������� ���, 2 - ������� ���

    std::cout << "������� ����� �����: ";
    std::cin >> number; // ������ ����� � ����������

    std::cout << "������� ����� ����, ������� �� ������ ��������: ";
    std::cin >> bitPosition; // ������ ����� ����

    std::cout << "�������� ����� (1 - ������� ���, 2 - ������� ���): ";
    std::cin >> maskChoice; // ������ ����� �����

    // ���������� ����� � ����������� �� ������
    int mask = (maskChoice == 1) ? 1 : (1 << 31);

    // �������� n-�� ���, ��������� ����������� ��������
    number = number & ~(1 << bitPosition);

    std::cout << "���������: " << number << std::endl;
}

int obnulitBity(int value, int bit1, int bit2, int bit3) {
    // ������� �����, � ������� ��� ���� ����������� � 1.
    int mask = -1; // ��� ���� ����������� � 1.
    bitset<16> binuryMask(mask);
    cout << binuryMask << endl;
    // �������� ��������� ����, ��������� ��������������� ���� � 0 � �����.
    mask &= ~(1 << bit1);
    mask &= ~(1 << bit2);
    mask &= ~(1 << bit3);
    bitset<16> binuryMaskChanged(mask);
    cout << binuryMaskChanged << endl;

    // ��������� ����� � ��������, ����� �������� ������������ ����.
    int result = value & mask;

    return result;
}

const int NUM_ELEMENTS = 8; // ���������� ��������� � �������

void prak2() {
    std::vector<unsigned char> numbers(NUM_ELEMENTS);

    std::cout << "������� " << NUM_ELEMENTS << " ����� (�� 0 �� 7): ";
    for (int i = 0; i < NUM_ELEMENTS; i++) {
        int num;
        std::cin >> num;

        if (num >= 0 && num <= 7) {
            numbers[i] = static_cast<unsigned char>(num);
        }
        else {
            std::cout << "����� ������ ���� �� 0 �� 7. ����������, ������� ����� �����: ";
            i--;
        }
    }

    std::cout << "�������� ������: ";
    printArray(numbers);

    bitwiseSort(numbers);

    std::cout << "��������������� ������: ";
    printArray(numbers);
}




// ������� ��� ������ ������� �� �����
void printArray(const std::vector<unsigned char>& arr) {
    for (unsigned char num : arr) {
        std::cout << static_cast<int>(num) << " ";
    }
    std::cout << std::endl;
}

// ������� ��� ���������� ������� � �������������� �������� �������
void bitwiseSort(std::vector<unsigned char>& arr) {
    const int BITS = 3; // ��� ��� ����� �� 0 �� 7, ��� ����� 3 ���� ��� �������������
    const int MAX_VALUE = (1 << BITS) - 1;

    std::vector<int> count(MAX_VALUE + 1, 0);

    // ������������ ���������� ������� �����
    for (unsigned char num : arr) {
        count[num]++;
    }

    int index = 0;
    for (int i = 0; i <= MAX_VALUE; i++) {
        while (count[i] > 0) {
            arr[index] = static_cast<unsigned char>(i);
            index++;
            count[i]--;
        }
    }
}