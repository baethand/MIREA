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
    # Test empty array
    assert bucketsort([], 10) == []

    # Test array with one element
    assert bucketsort([5], 10) == [5]

    # Test array with multiple elements
    assert bucketsort([3, 6, 2, 7, 1, 4], 10) == [1, 2, 3, 4, 6, 7]

    # Test array with duplicate elements
    assert bucketsort([3, 6, 2, 7, 1, 4, 6, 3], 10) == [1, 2, 3, 3, 4, 6, 6, 7]

    # Test array with all elements being the same
    assert bucketsort([3, 3, 3, 3, 3], 10) == [3, 3, 3, 3, 3]

    # Test array with random elements
    random_arr = [random.randint(0, 100) for _ in range(20)]
    sorted_random_arr = sorted(random_arr)
    assert bucketsort(random_arr, 101) == sorted_random_arr

    print("All tests passed!")
    print("Sorting random array:", random_arr)
    print("Sorted array:", bucketsort(random_arr, 101))

test_bucketsort()