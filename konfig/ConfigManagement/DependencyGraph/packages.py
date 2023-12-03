import requests
import argparse
import re


def get_dependencies(pkg_manager, package_name):
    try:
        dependencies = ""
        if pkg_manager == "pip":
            response = requests.get(f"https://pypi.org/pypi/{package_name}/json")
            data = response.json()
            dependencies = data['info']['requires_dist']
        if pkg_manager == "npm":
            response = requests.get(f"https://api.npms.io/v2/package/{package_name}")
            data = response.json()
            dependencies = data['collected']['metadata']['dependencies']

        return dependencies
    except KeyError:
        return "error"


def create_graph(pkg_manager, package_name):
    counter = 0
    a = set()
    packages = [package_name]
    visited_packages = {package_name}
    graph = [f"digraph {package_name} {{"]
    for package in packages:
        print(package)
        dependencies = get_dependencies(pkg_manager, package)
        if dependencies == "error":
            pass
        else:
            if dependencies:
                for dep in dependencies:
                    dep_name = re.findall(r'[\w-]+', dep)[0]
                    if not dep_name in a:
                        graph.append(f"    \"{package}\" -> \"{dep_name}\";")
                        if dep_name not in visited_packages:
                            if counter <= 20:
                                packages.append(dep_name)
                                counter += 1
                        visited_packages |= {dep_name}
                        a.add(dep_name)

    graph.append("}")
    return "\n".join(graph)



if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="packages - Dependencies utility via Graphviz format.")
    parser.add_argument("package_manager", help="Package manager (pip, npm)")
    parser.add_argument("package_name", help="Package name dependencies to be formed")
    args = parser.parse_args()

    print(create_graph(args.package_manager, args.package_name))