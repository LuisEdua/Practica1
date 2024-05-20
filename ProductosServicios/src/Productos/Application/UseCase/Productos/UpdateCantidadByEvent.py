from src.Productos.Domain.Ports.ProductosPort import ProductosPort as Port


class UpdateCantidadByEvent:
    def __init__(self, repository: Port):
        self.repository = repository

    def run(self, data):
        self.repository.update_cantidad(data['id'], data['cantidad'])
