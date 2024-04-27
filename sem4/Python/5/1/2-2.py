import random

def bucketsort(arr, k):
    counts = [0] * k
    for x in arr:
        counts[x] += 1

    sorted_arr = []
    for i in range(k):
        sorted_arr.extend([i] * counts[i])

    return sorted_arr

def test_bucketsort():
    # Test on random data
    for _ in range(100):
        arr = [random.randint(0, 99) for _ in range(100)]
        k = max(arr) + 1
        sorted_arr = bucketsort(arr, k)
        assert sorted_arr == sorted(arr), f"Failed on test {arr}. Got {sorted_arr}, expected {sorted(arr)}"

    # Test on edge cases
    assert bucketsort([], 0) == []
    assert bucketsort([1, 1, 1, 1], 2) == [1, 1, 1, 1]
    assert bucketsort([4, 3, 2, 1], 5) == [1, 2, 3, 4]

    print("All tests passed.")

test_bucketsort()