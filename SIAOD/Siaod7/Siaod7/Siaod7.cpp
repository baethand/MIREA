#include <iostream>
#include <vector>
#include <algorithm>
#include <random>

using namespace std;

// Глобальные счетчики для подсчета переборов
int bruteForceCounter = 0;
int dpCounter = 0;

// Генератор случайных чисел
random_device rd;
mt19937 gen(rd());

int getRandomNumber() {
    uniform_int_distribution<int> number(1, 100);
    return number(gen);
}

int minWeightPathBruteForce(const vector<vector<int>>& grid, int i, int j) {
    bruteForceCounter++;

    int n = grid.size();
    int m = grid[0].size();

    // Базовый случай: достигнута правая нижняя клетка
    if (i == n - 1 && j == m - 1) {
        return grid[i][j];
    }

    // Рекуррентное соотношение: шаг вправо, вниз или по диагонали вправо-вниз
    int right = (j < m - 1) ? minWeightPathBruteForce(grid, i, j + 1) : INT_MAX;
    int down = (i < n - 1) ? minWeightPathBruteForce(grid, i + 1, j) : INT_MAX;
    int diagonal = (i < n - 1 && j < m - 1) ? minWeightPathBruteForce(grid, i + 1, j + 1) : INT_MAX;

    // Возвращаем минимальный вес для текущей клетки
    return grid[i][j] + min({ right, down, diagonal });
}

int minWeightPath(const vector<vector<int>>& grid) {
    int n = grid.size();
    int m = grid[0].size();

    // Создаем таблицу для хранения минимальных сумм весов
    vector<vector<int>> dp(n, vector<int>(m, 0));

    // Заполняем базовые случаи
    dp[0][0] = grid[0][0];
    for (int i = 1; i < n; ++i)
        dp[i][0] = dp[i - 1][0] + grid[i][0];
    for (int j = 1; j < m; ++j)
        dp[0][j] = dp[0][j - 1] + grid[0][j];

    // Заполняем таблицу по рекуррентному соотношению
    for (int i = 1; i < n; ++i) {
        for (int j = 1; j < m; ++j) {
            dp[i][j] = grid[i][j] + min({ dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1] });
            dpCounter++;
        }
    }

    // Возвращаем минимальный вес для пути до правой нижней клетки
    return dp[n - 1][m - 1];
}

int main() {
    setlocale(LC_ALL, "Rus");
    int n, m;

    cout << "Введите количество строк поля: ";
    cin >> n;

    cout << "Введите количество столбцов поля: ";
    cin >> m;

    // Создаем поле с рандомными значениями от 1 до 100
    vector<vector<int>> grid(n, vector<int>(m, 0));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            grid[i][j] = getRandomNumber();
        }
    }

    // Считаем количество переборов для метода "в лоб"
    int resultBruteForce = minWeightPathBruteForce(grid, 0, 0);

    // Считаем количество переборов для метода динамического программирования
    int resultDP = minWeightPath(grid);

    cout << "\nСгенерированное поле:\n";
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cout << grid[i][j] << " ";
        }
        cout << endl;
    }

    cout << "\nМинимальный вес маршрута (в лоб): " << resultBruteForce << endl;
    cout << "Количество переборов (в лоб): " << bruteForceCounter << endl;

    cout << "\nМинимальный вес маршрута (динамическое программирование): " << resultDP << endl;
    cout << "Количество переборов (динамическое программирование): " << dpCounter << endl;

    return 0;
}
