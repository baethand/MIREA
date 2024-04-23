from some_module import GLOBAL_VAR
GLOBAL_VAR = 42
#incorrect
#изменяется локальная копия, а не глобальная переменная в модуле

import some_module
some_module.GLOBAL_VAR = 42
#correct
#изменяем переменную непосредсвенно в модуле

