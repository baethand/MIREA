import pytest
from deal import ContractError

from test import divide, get_first, square_root, divide_by_nonzero, multiply, square_root_v2

def test_divide():
    with pytest.raises(ContractError):
        divide(-10, 2)

def test_get_first():
    with pytest.raises(ContractError):
        get_first([])

def test_square_root():
    with pytest.raises(ContractError):
        square_root(-16)

def test_divide_by_nonzero():
    with pytest.raises(ValueError):
        divide_by_nonzero(10, 0)

def test_multiply():
    with pytest.raises(ContractError):
        multiply(-2, 3)

def test_square_root_v2():
    with pytest.raises(ValueError):
        square_root_v2(-16)

if __name__ == "__main__":
    pytest.main()