from deal import pre, post, raises

@pre(lambda x: x > 0)
@post(lambda x, result: result > 0)
def divide(x, y):
    return x / y

@pre(lambda lst: len(lst) > 0)
@post(lambda lst, result: result in lst)
def get_first(lst):
    return lst[0]

@pre(lambda x: x >= 0)
@post(lambda x, result: result >= 0)
def square_root(x):
    return x ** 0.5

@pre(lambda x: x != 0)
@raises(ValueError)
def divide_by_nonzero(x, y):
    return x / y

@pre(lambda x, y: x > 0 and y > 0)
@post(lambda x, y, result: result > 0)
def multiply(x, y):
    return x * y

@pre(lambda x: x >= 0)
@raises(ValueError)
def square_root_v2(x):
    if x < 0:
        raise ValueError("Input must be non-negative")
    return x ** 0.5

if __name__ == "__main__":
    print(divide(10, 2))
    print(get_first([1, 2, 3]))
    print(square_root(16))