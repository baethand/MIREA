def get_inheritance(cls):
    return ' -> '.join([cls.__name__] + [parent.__name__ for parent in cls.__mro__[1:]])

# Пример использования
print(get_inheritance(OSError))
