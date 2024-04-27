class BankAccount:
    def __init__(self, account_number, balance=0):
        self.account_number = account_number
        self._balance = balance

    @property
    def balance(self):
        return self._balance

    def deposit(self, amount):
        """Deposits the specified amount into the account."""
        self._balance += amount
        return f"{amount} средств успешно зачислены на счет {self.account_number}"

    def withdraw(self, amount):
        """Withdraws the specified amount from the account."""
        if amount > self._balance:
            raise ValueError("Недостаточно средств на счете для снятия")
        self._balance -= amount
        return f"{amount} средств успешно сняты с счета {self.account_number}"

    def check_balance(self):
        """Returns the current balance of the account."""
        return f"Баланс счета {self.account_number}: {self._balance}"

    def __repr__(self):
        return f"BankAccount(account_number={self.account_number}, balance={self._balance})"

    def __eq__(self, other):
        return isinstance(other, BankAccount) and self.account_number == other.account_number

    def __hash__(self):
        return hash(self.account_number)

    def __lt__(self, other):
        if isinstance(other, BankAccount):
            return self._balance < other._balance
        return NotImplemented

    def __gt__(self, other):
        if isinstance(other, BankAccount):
            return self._balance > other._balance
        return NotImplemented

    def __le__(self, other):
        if isinstance(other, BankAccount):
            return self._balance <= other._balance
        return NotImplemented

    def __ge__(self, other):
        if isinstance(other, BankAccount):
            return self._balance >= other._balance
        return NotImplemented

    def __add__(self, other):
        if isinstance(other, (int, float)):
            return self._balance + other
        elif isinstance(other, BankAccount):
            new_balance = self._balance + other._balance
            return BankAccount(self.account_number, new_balance)  # Возвращаем новый экземпляр
        return NotImplemented

    def __sub__(self, other):
        if isinstance(other, (int, float)):
            new_balance = self._balance - other
            return BankAccount(self.account_number, new_balance)  # Возвращаем новый экземпляр
        elif isinstance(other, BankAccount):
            new_balance = self._balance - other._balance
            return BankAccount(self.account_number, new_balance)  # Возвращаем новый экземпляр
        return NotImplemented

    def __iadd__(self, other):
        if isinstance(other, (int, float)):
            self._balance += other
            return self
        elif isinstance(other, BankAccount):
            self._balance += other._balance
            return self
        return NotImplemented

    def __isub__(self, other):
        if isinstance(other, (int, float)):
            self._balance -= other
            return self
        elif isinstance(other, BankAccount):
            self._balance -= other._balance
            return self
        return NotImplemented

    def __mul__(self, other):
        if isinstance(other, (int, float)):
            return self._balance * other
        return NotImplemented

    def __imul__(self, other):
        if isinstance(other, (int, float)):
            self._balance *= other
            return self
        return NotImplemented

    def __rmul__(self, other):
        if isinstance(other, (int, float)):
            return other * self._balance
        return NotImplemented

# Создание нового банковского счета
account1 = BankAccount("123456", 100)
print(account1.check_balance())  # Печать баланса счета

# Пополнение счета
print(account1.deposit(50))  # Печать сообщения о зачислении средств
print(account1.check_balance())  # Печать обновленного баланса счета

# Снятие средств со счета
try:
    print(account1.withdraw(120))  # Попытка снять больше средств, чем есть на счете
except ValueError as e:
    print(e)  # Печать сообщения об ошибке

# Создание второго банковского счета
account2 = BankAccount("654321", 200)

# Проверка операций сравнения
print(account1 > account2)  # Сравнение балансов двух счетов

# Использование операций сложения и вычитания
account3 = account1 + account2
print(account3.check_balance())  # Печать баланса нового счета после сложения двух счетов
account4 = account3 - 50
print(account4.check_balance())  # Печать баланса счета после вычитания суммы

# Проверка инварианта класса
account5 = BankAccount("987654", -50)  # Создание счета с отрицательным балансом
print(account5.check_balance())  # Печать баланса счета