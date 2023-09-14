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
		<< "Значение переменной в двоичной системе: " << binuryRes;
}

void prog2() {
    int value;
    std::cout << "Введите значение: ";
    std::cin >> value;

    int bit1 = 9;  // 9-ый бит (считая с 0).
    int bit2 = 11; // 11-ый бит (считая с 0).
    int bit3 = 3;  // 3-ий бит (считая с 0).

    int result = obnulitBity(value, bit1, bit2, bit3);

    std::cout << "Результат: " << result << std::endl;
}

void prog3() {
    int number; // Объявляем переменную целого типа
    int multiplier = 128; // Задаем множитель

    std::cout << "Введите целое число: ";
    std::cin >> number; // Вводим число с клавиатуры

    // Умножаем значение переменной на множитель с помощью поразрядной операции
    number = number << 7; // 128 в двоичной системе равно 10000000, поэтому мы сдвигаем биты на 7 позиций влево

    std::cout << "Результат умножения: " << number << std::endl;
}

void prog4() {
    int number; // Объявляем переменную целого типа

    std::cout << "Введите целое число: ";
    std::cin >> number; // Вводим число с клавиатуры

    int divisor = 512; // Задаем делитель

    // Деление значения переменной на делитель с помощью поразрядной операции
    number = number >> 9; // 512 в двоичной системе равно 1000000000, поэтому мы сдвигаем биты на 9 позиций вправо

    std::cout << "Результат деления: " << number << std::endl;
}

void prog5() {
    int number; // Объявляем переменную целого типа
    int bitPosition; // Номер бита, который нужно обнулить (начиная с 0)
    int maskChoice; // Выбор маски: 1 - младший бит, 2 - старший бит

    std::cout << "Введите целое число: ";
    std::cin >> number; // Вводим число с клавиатуры

    std::cout << "Введите номер бита, который вы хотите обнулить: ";
    std::cin >> bitPosition; // Вводим номер бита

    std::cout << "Выберите маску (1 - младший бит, 2 - старший бит): ";
    std::cin >> maskChoice; // Вводим выбор маски

    // Определяем маску в зависимости от выбора
    int mask = (maskChoice == 1) ? 1 : (1 << 31);

    // Обнуляем n-ый бит, используя поразрядную операцию
    number = number & ~(1 << bitPosition);

    std::cout << "Результат: " << number << std::endl;
}

int obnulitBity(int value, int bit1, int bit2, int bit3) {
    // Создаем маску, в которой все биты установлены в 1.
    int mask = -1; // Все биты установлены в 1.
    bitset<16> binuryMask(mask);
    cout << binuryMask << endl;
    // Обнуляем указанные биты, установив соответствующие биты в 0 в маске.
    mask &= ~(1 << bit1);
    mask &= ~(1 << bit2);
    mask &= ~(1 << bit3);
    bitset<16> binuryMaskChanged(mask);
    cout << binuryMaskChanged << endl;

    // Применяем маску к значению, чтобы обнулить определенные биты.
    int result = value & mask;

    return result;
}

const int NUM_ELEMENTS = 8; // Количество элементов в массиве

void prak2() {
    std::vector<unsigned char> numbers(NUM_ELEMENTS);

    std::cout << "Введите " << NUM_ELEMENTS << " чисел (от 0 до 7): ";
    for (int i = 0; i < NUM_ELEMENTS; i++) {
        int num;
        std::cin >> num;

        if (num >= 0 && num <= 7) {
            numbers[i] = static_cast<unsigned char>(num);
        }
        else {
            std::cout << "Число должно быть от 0 до 7. Пожалуйста, введите число снова: ";
            i--;
        }
    }

    std::cout << "Исходный массив: ";
    printArray(numbers);

    bitwiseSort(numbers);

    std::cout << "Отсортированный массив: ";
    printArray(numbers);
}




// Функция для вывода массива на экран
void printArray(const std::vector<unsigned char>& arr) {
    for (unsigned char num : arr) {
        std::cout << static_cast<int>(num) << " ";
    }
    std::cout << std::endl;
}

// Функция для сортировки массива с использованием битового массива
void bitwiseSort(std::vector<unsigned char>& arr) {
    const int BITS = 3; // Так как числа от 0 до 7, нам нужно 3 бита для представления
    const int MAX_VALUE = (1 << BITS) - 1;

    std::vector<int> count(MAX_VALUE + 1, 0);

    // Подсчитываем количество каждого числа
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