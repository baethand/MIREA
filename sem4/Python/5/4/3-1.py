def deal(contract):
    def decorator(func):
        def wrapper(*args, **kwargs):
            assert contract(*args, **kwargs), "Contract violation: deal failed"
            return func(*args, **kwargs)
        return wrapper
    return decorator

@deal(lambda x, y: x + y > 0)
def add(x, y):
    return x + y

print(add(5, 7))  # Output: 12
print(add(-2, 3))  # Raises AssertionError: Contract violation: deal failed