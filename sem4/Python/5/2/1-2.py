файл: test_prime

import pytest
from prime_checker import divide

@pytest.fixture
def numbers():
    return [(10, 2), (20, 4), (30, 0)]

def test_divide(numbers):
    for x, y in numbers:
        assert divide(x, y) == x / y if y != 0 else None

def test_divide_by_zero():
    assert divide(10, 0) is None


файл: prime_checker

def divide(x, y):
    if y == 0:
        return None
    return x / y