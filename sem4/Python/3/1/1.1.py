import sys

print("First import:")
import module

print("\nSecond import:")
import module

print("\nImport using 'importlib':")
import importlib

importlib.import_module("module")

print("\nCheck if module is in sys.modules:")
print("module" in sys.modules)

"""
    Модуль загружается только один раз при первом импорте, а затем кэшируется в системном словаре sys.modules.При последующих
    импортах Python использует уже загруженный и обработанный модуль из кэша.
"""
