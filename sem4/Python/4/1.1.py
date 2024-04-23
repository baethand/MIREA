class MyClass:
    def __init__(self):
        self.field1 = 1
        self._field2 = 2
        self.__field3 = 3

obj = MyClass()

# Вывод всех имен полей, кроме служебных
print([attr for attr in dir(obj) if not attr.startswith('__')])
