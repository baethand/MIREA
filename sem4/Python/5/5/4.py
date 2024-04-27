import unittest
import sqlite3

class BankAccount:
    def __init__(self, account_number, conn, cursor):
        self.account_number = account_number
        self.conn = conn
        self.cursor = cursor

    def deposit(self, amount):
        self.cursor.execute(
            "UPDATE accounts SET balance = balance + ? WHERE account_number = ?", (amount, self.account_number))
        self.conn.commit()
        return f"{amount} средств успешно зачислены на счет {self.account_number}"

    def withdraw(self, amount):
        self.cursor.execute(
            "SELECT balance FROM accounts WHERE account_number = ?", (self.account_number,))
        balance_row = self.cursor.fetchone()
        if balance_row:
            balance = balance_row[0]
            if balance >= amount:
                self.cursor.execute(
                    "UPDATE accounts SET balance = balance - ? WHERE account_number = ?", (amount, self.account_number))
                self.conn.commit()
                return f"{amount} средств успешно сняты с счета {self.account_number}"
            else:
                return "Недостаточно средств на счете"
        else:
            return "Счет не найден"

    def check_balance(self):
        self.cursor.execute(
            "SELECT balance FROM accounts WHERE account_number = ?", (self.account_number,))
        balance_row = self.cursor.fetchone()
        if balance_row:
            balance = balance_row[0]
            return f"Баланс счета {self.account_number}: {balance}"
        else:
            return "Счет не найден"

    def close_account(self):
        self.cursor.execute(
            "DELETE FROM accounts WHERE account_number = ?", (self.account_number,))
        self.conn.commit()
        return f"Счет {self.account_number} закрыт"

    def create_account(self, balance):
        self.cursor.execute(
            "INSERT INTO accounts (account_number, balance) VALUES (?, ?)", (self.account_number, balance))
        self.conn.commit()
        return f"Счет {self.account_number} успешно создан"

class TestBankAccount(unittest.TestCase):
    def setUp(self):
        # Подготовка базы данных для тестов
        self.conn = sqlite3.connect(':memory:')
        self.cursor = self.conn.cursor()
        self.cursor.execute(
            "CREATE TABLE accounts (account_number INTEGER PRIMARY KEY, balance REAL)")
        self.conn.commit()

        # Создание тестового счета
        self.test_account_number = 123456
        self.test_account = BankAccount(self.test_account_number, self.conn, self.cursor)
        self.test_account.create_account(1000)

    def test_deposit(self):
        # Проверка внесения денег на счет
        self.test_account.deposit(500)
        self.cursor.execute("SELECT balance FROM accounts WHERE account_number = ?", (self.test_account_number,))
        balance_row = self.cursor.fetchone()
        if balance_row:
            balance = balance_row[0]
            self.assertEqual(balance, 1500)
        else:
            self.fail("Баланс не найден")

    def test_withdraw(self):
        # Проверка снятия денег со счета
        self.test_account.deposit(500)
        self.test_account.withdraw(300)
        self.cursor.execute("SELECT balance FROM accounts WHERE account_number = ?", (self.test_account_number,))
        balance_row = self.cursor.fetchone()
        if balance_row:
            balance = balance_row[0]
            self.assertEqual(balance, 1200)
        else:
            self.fail("Баланс не найден")

    def test_check_balance(self):
        # Проверка запроса баланса счета
        balance_message = self.test_account.check_balance()
        self.assertEqual(balance_message, "Баланс счета 123456: 1000.0")

    def test_close_account(self):
        # Проверка закрытия счета
        close_message = self.test_account.close_account()
        self.assertEqual(close_message, "Счет 123456 закрыт")
        # Проверяем, что счет действительно закрыт
        self.cursor.execute("SELECT * FROM accounts WHERE account_number = ?", (self.test_account_number,))
        self.assertIsNone(self.cursor.fetchone())

    def tearDown(self):
        self.conn.close()

if __name__ == '__main__':
    unittest.main()