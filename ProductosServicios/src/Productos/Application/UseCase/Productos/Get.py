from src.Productos.Domain.Ports.ProductosPort import ProductosPort as Port


class GetUseCase:
    def __init__(self, repository: Port):
        self.__repository = repository

    def run(self):
        return self.__repository.get_productos()
