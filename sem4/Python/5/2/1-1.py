файл: prime_checker

def is_prime(number):
    if number <= 1:
        return False
    for i in range(2, int(number ** 0.5) + 1):
        if number % i == 0:
            return False
    return True



файл: test_prime

import pytest
from prime_checker import is_prime

@pytest.mark.parametrize("number, expected_result", [
    (2, True),
    (3, True),
    (4, False),
    (5, True),
    (6, False),
    (7, True),
    (8, False),
    (9, False),
    (10, False),
    (11, True),
])
def test_is_prime(number, expected_result):
    assert is_prime(number) == expected_result

@pytest.fixture
def prime_numbers():
    return [2, 3, 5, 7, 11]

def test_prime_list(prime_numbers):
    for number in prime_numbers:
        assert is_prime(number) == True

def test_non_prime():
    assert is_prime(4) == False
    assert is_prime(6) == False
    assert is_prime(8) == False