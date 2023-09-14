#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <chrono>

using namespace std;

// Функция для сортировки числового файла
void externalSort(const string& inputFilename, const string& outputFilename) {
    ifstream inputFile(inputFilename);
    if (!inputFile.is_open()) {
        cerr << "Не удалось открыть входной файл." << endl;
        return;
    }

    vector<int> data;
    int num;
    while (inputFile >> num) {
        data.push_back(num);
    }
    inputFile.close();

    sort(data.begin(), data.end());

    ofstream outputFile(outputFilename);
    if (!outputFile.is_open()) {
        cerr << "Не удалось создать выходной файл." << endl;
        return;
    }

    for (int num : data) {
        outputFile << num << endl;
    }
    outputFile.close();
}

int main() {
    setlocale(LC_ALL, "RUS");
    // Тестовый пример с входными данными
    vector<int> testData = { 5, 2, 8, 1, 9, 3, 6, 7, 4 };
    ofstream testInputFile("test_input.txt");
    if (testInputFile.is_open()) {
        for (int num : testData) {
            testInputFile << num << endl;
        }
        testInputFile.close();
    }

    // Задача сортировки для входных данных объемом 100 чисел
    const string inputFilename100 = "input_100.txt";
    const string outputFilename100 = "output_100.txt";
    ofstream inputFile100(inputFilename100);
    if (inputFile100.is_open()) {
        for (int i = 0; i < 100; i++) {
            inputFile100 << rand() % 1000 << endl;
        }
        inputFile100.close();
    }

    auto start100 = chrono::high_resolution_clock::now();
    externalSort(inputFilename100, outputFilename100);
    auto stop100 = chrono::high_resolution_clock::now();
    auto duration100 = chrono::duration_cast<chrono::milliseconds>(stop100 - start100);

    cout << "Время сортировки для 100 чисел: " << duration100.count() << " мс" << endl;

    // Задача сортировки для входных данных объемом 1000 чисел
    const string inputFilename1000 = "input_1000.txt";
    const string outputFilename1000 = "output_1000.txt";
    ofstream inputFile1000(inputFilename1000);
    if (inputFile1000.is_open()) {
        for (int i = 0; i < 1000; i++) {
            inputFile1000 << rand() % 1000 << endl;
        }
        inputFile1000.close();
    }

    auto start1000 = chrono::high_resolution_clock::now();
    externalSort(inputFilename1000, outputFilename1000);
    auto stop1000 = chrono::high_resolution_clock::now();
    auto duration1000 = chrono::duration_cast<chrono::milliseconds>(stop1000 - start1000);

    cout << "Время сортировки для 1000 чисел: " << duration1000.count() << " мс" << endl;

    // Задача сортировки заданного числового файла
    const string inputFilename = "input.txt";
    const string outputFilename = "output.txt";

    auto start = chrono::high_resolution_clock::now();
    externalSort(inputFilename, outputFilename);
    auto stop = chrono::high_resolution_clock::now();
    auto duration = chrono::duration_cast<chrono::milliseconds>(stop - start);

    cout << "Сортировка завершена. Результат записан в файл " << outputFilename << endl;
    cout << "Время сортировки: " << duration.count() << " мс" << endl;

    return 0;
}
