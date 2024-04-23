class HashTable:
    def __init__(self):
        self.size = 10
        self.table = [[] for _ in range(self.size)]

    def _hash(self, key):
        return hash(key) % self.size

    def __delitem__(self, key):
        idx = self._hash(key)
        for i, (k, _) in enumerate(self.table[idx]):
            if k == key:
                del self.table[idx][i]
                return
        raise KeyError(key)

    def __getitem__(self, key):
        idx = self._hash(key)
        for k, v in self.table[idx]:
            if k == key:
                return v
        raise KeyError(key)

    def __setitem__(self, key, value):
        idx = self._hash(key)
        for i, (k, v) in enumerate(self.table[idx]):
            if k == key:
                self.table[idx][i] = (key, value)
                return
        self.table[idx].append((key, value))

    def __len__(self):
        return sum(len(bucket) for bucket in self.table)

    def __iter__(self):
        for bucket in self.table:
            for key, _ in bucket:
                yield key

    def get(self, key, default=None):
        try:
            return self[key]
        except KeyError:
            return default


def test_hash_table():
    ht = HashTable()

    ht['a'] = 1
    assert ht['a'] == 1

    ht['b'] = 2
    assert ht['b'] == 2

    assert len(ht) == 2

    keys = set()
    for key in ht:
        keys.add(key)
    assert keys == {'a', 'b'}

    assert ht.get('a') == 1
    assert ('a' in ht) == True
    assert ('c' in ht) == False

    del ht['a']
    assert ('a' in ht) == False
    assert len(ht) == 1

    print("Все тесты пройдены успешно!")

test_hash_table()
