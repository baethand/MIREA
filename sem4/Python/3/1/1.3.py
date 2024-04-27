from math import pi
GLOBAL_VAR = 42
#incorrect
#изменяется локальная копия, а не глобальная переменная в модуле

import math
math.GLOBAL_VAR = 42
#correct
#изменяем переменную непосредсвенно в модуле

