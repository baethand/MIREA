import networkx as nx
import matplotlib.pyplot as plt

from collections import deque

# Функция перехода из комнаты в комнату
def go(room):
    def func(state):
        return dict(state, room=room)
    return func


# Структура игры. Комнаты и допустимые в них действия
game = {
    'room0': dict(
        left=go('room1'),
        up=go('room2'),
        right=go('room3')
    ),
    'room1': dict(
        up=go('room2'),
        right=go('room0')
    ),
    'room2': dict(
    ),
    'room3': dict(
        up=go('room4'),
        right=go('room5')
    ),
    'room4': dict(
        down=go('room3'),
        right=go('room5')
    ),
    'room5': dict(
        up=go('room4'),
        left=go('room3')
    )
}

# Стартовое состояние
START_STATE = dict(room='room0')


def is_goal_state(state):
    '''
    Проверить, является ли состояние целевым.
    '''
    return state['room'] == 'room2'


def get_current_room(state):
    '''
    Выдать комнату, в которой находится игрок.
    '''
    return state['room']


def make_model(game, start_state):
    '''
    Построить граф всех возможных состояний игры.
    '''
    graph = nx.DiGraph()  # Создаем ориентированный граф

    # Очередь для BFS
    queue = deque([(start_state,)])

    # Функция для хеширования состояния
    def hash_state(state):
        return tuple(sorted(state.items()))

    # BFS
    while queue:
        current_path = queue.popleft()
        current_state = current_path[-1]  # Получаем последнее состояние в пути

        # Добавляем текущее состояние в граф
        current_hash = hash_state(current_state)
        graph.add_node(current_hash)

        # Получаем доступные действия для текущей комнаты
        actions = game.get(get_current_room(current_state), {})

        # Добавляем ребра в граф для всех возможных действий
        for action, func in actions.items():
            new_state = func(current_state)
            new_hash = hash_state(new_state)
            graph.add_edge(current_hash, new_hash, action=action)

            # Добавляем новое состояние в очередь, если оно еще не посещено
            if new_hash not in graph.nodes:
                queue.append(current_path + (new_state,))

    return graph

# Создаем граф состояний
graph = make_model(game, START_STATE)

# Рисуем граф
plt.figure(figsize=(10, 6))
pos = nx.spring_layout(graph)
nx.draw(graph, pos, with_labels=True, node_size=5000, node_color="skyblue", font_size=10, font_weight="bold", arrowsize=20)
nx.draw_networkx_edge_labels(graph, pos, font_color='red')
plt.title("Game State Graph")
plt.show()