#include<iostream>
#include <bitset>
#include <locale>

using namespace std;

void prog2();
void prog1();
int obnulitBity(int value, int bit1, int bit2, int bit3);

int main() {
    setlocale(LC_ALL, "Russian");
    prog2();
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

    int bit1 = 8;  // 9-�� ��� (������ � 0).
    int bit2 = 10; // 11-�� ��� (������ � 0).
    int bit3 = 2;  // 3-�� ��� (������ � 0).

    int result = obnulitBity(value, bit1, bit2, bit3);

    std::cout << "���������: " << result << std::endl;
}

int obnulitBity(int value, int bit1, int bit2, int bit3) {
    // ������� �����, � ������� ��� ���� ����������� � 1.
    int mask = -1; // ��� ���� ����������� � 1.

    // �������� ��������� ����, ��������� ��������������� ���� � 0 � �����.
    mask &= ~(1 << bit1);
    mask &= ~(1 << bit2);
    mask &= ~(1 << bit3);

    // ��������� ����� � ��������, ����� �������� ������������ ����.
    int result = value & mask;

    return result;
}

//xxxx xxxx xxxx xxxx abcd
//		  xxxx xxxx ab
//1111 0000 0000 0000
//0000 0000 1111 0000

//1110 1111 0101 1111