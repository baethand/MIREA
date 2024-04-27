import networkx as nx
import matplotlib.pyplot as plt
from collections import deque

# Функция перехода из комнаты в комнату
def go(room):
    def func(state):
        return dict(state, room=room)
    return func

# Функция для активации рычага
def activate_lever(state):
    if state['room'] == 'room3':
        return dict(state, left=go('room0'))  # Делаем односторонний переход двусторонним
    else:
        return state

# Обновленная структура игры
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
    'room2': dict(),
    'room3': dict(
        up=go('room4'),
        right=go('room5'),
        lever=activate_lever  # Добавляем рычаг в комнату room3
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

# Построение графа состояний и поиск тупиковых узлов остается без изменений


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

# Функция для хеширования состояния
def hash_state(state):
    return tuple(sorted(state.items()))

# Обновленная функция make_model с учетом hash_state
def make_model(game, start_state):
    '''
    Построить граф всех возможных состояний игры.
    '''
    graph = nx.DiGraph()  # Создаем ориентированный граф

    # Очередь для BFS
    queue = deque([(start_state,)])

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

# Построение графа состояний и поиск тупиковых узлов остается без изменений


def find_dead_ends(graph, start_state):
    """
    Найти тупиковые узлы в графе.

    :param graph: Граф всех возможных состояний игры.
    :param start_state: Начальное состояние игры.
    :return: Список тупиковых узлов.
    """
    dead_ends = []  # Список для хранения тупиковых узлов

    # Функция для поиска тупиковых узлов с использованием DFS
    def dfs(current_state, visited):
        if current_state in visited:
            return
        visited.add(current_state)

        # Если текущее состояние - тупиковый узел, добавляем его в список
        if len(graph[current_state]) == 0:
            dead_ends.append(current_state)
            return

        # Рекурсивно ищем тупиковые узлы для каждого соседнего состояния
        for neighbor_state in graph[current_state]:
            dfs(neighbor_state, visited.copy())

    # Запускаем DFS для каждого узла графа
    for node in graph.nodes:
        dfs(node, set())

    return dead_ends

# Создаем граф состояний
graph = make_model(game, START_STATE)

# Находим тупиковые узлы
dead_ends = find_dead_ends(graph, START_STATE)

# Рисуем граф с обозначением тупиковых узлов и рычага
plt.figure(figsize=(10, 6))
pos = nx.spring_layout(graph)

# Рисуем узлы графа
nx.draw(graph, pos, with_labels=True, node_size=5000, node_color="skyblue", font_size=10, font_weight="bold", arrowsize=20)

# Рисуем тупиковые узлы красным цветом и большим размером
nx.draw_networkx_nodes(graph, pos, nodelist=dead_ends, node_size=7000, node_color="red")

# Добавляем отображение рычага в комнате room3
nx.draw_networkx_nodes(graph, pos, nodelist=[hash_state(go('room3')(START_STATE))], node_size=7000, node_color="green")

nx.draw_networkx_edge_labels(graph, pos, font_color='red')
plt.title("Game State Graph")
plt.show()

print("Тупиковые узлы:", dead_ends)