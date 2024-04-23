import inspect
import importlib.util

def generate_documentation(module_name):
    spec = importlib.util.spec_from_file_location(module_name, module_name)
    module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(module)

    doc = inspect.getdoc(module)
    if doc:
        print(f"# Модуль {module_name}\n\n{doc}\n")

    for name, obj in inspect.getmembers(module):
        if inspect.isclass(obj):
            print(f"## Класс {name}\n")
            class_doc = inspect.getdoc(obj)
            if class_doc:
                print(f"{class_doc}\n")
            for member_name, member_obj in inspect.getmembers(obj):
                if inspect.isfunction(member_obj) or inspect.ismethod(member_obj):
                    print(f"* **Метод** `{member_name}{inspect.signature(member_obj)}`\n")
                    method_doc = inspect.getdoc(member_obj)
                    if method_doc:
                        print(f"{method_doc}\n")
        elif inspect.isfunction(obj):
            print(f"## Функция {name}\n")
            print(f"Сигнатура: `{name}{inspect.signature(obj)}`\n")
            func_doc = inspect.getdoc(obj)
            if func_doc:
                print(f"{func_doc}\n")

if __name__ == "__main__":
    generate_documentation("4.py")
