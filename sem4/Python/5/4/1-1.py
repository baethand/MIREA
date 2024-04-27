import deal
import pytest

# Пример функции с контрактами
@deal.pre(lambda x: x > 0, reason="Input value must be greater than 0")
@deal.post(lambda result: result > 0, reason="Result must be greater than 0")
@deal.ensure(lambda result, old_result: result > old_result, reason="Result must be greater than previous result")
@deal.raises(ValueError, lambda x: x == 0, reason="Input cannot be zero")
def divide(a: int, b: int) -> int:
    if b == 0:
        raise ValueError("Division by zero")
    return a / b

# Тесты
def test_divide():
    # Проверка pre-условия: ввод не должен быть равен 0
    with pytest.raises(deal.PreContractError, match="Input value must be greater than 0"):
        divide(0, 5)

    # Проверка post-условия: результат деления должен быть больше 0
    with pytest.raises(deal.PostContractError, match="Result must be greater than 0"):
        divide(10, -2)

    # Проверка ensure-условия: результат должен быть больше предыдущего результата
    with pytest.raises(deal.EnsureContractError, match="Result must be greater than previous result"):
        divide(10, 5)

    # Проверка raises-условия: должно возникнуть исключение ValueError при вводе 0
    with pytest.raises(ValueError, match="Input cannot be zero"):
        divide(10, 0)

    # Проверка корректной работы функции
    assert divide(10, 2) == 5

if __name__ == "__main__":
    pytest.main()