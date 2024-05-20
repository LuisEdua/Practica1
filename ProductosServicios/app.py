from flask import Flask
from flask_cors import CORS
from src.Productos.Infrestructure.Routes.ProductosRoutes import product_routes, start_consumer_queue_change_orden_status_thread

app = Flask(__name__)
app.register_blueprint(product_routes, url_prefix="/productos")
CORS(app)

start_consumer_queue_change_orden_status_thread()

if __name__ == '__main__':
    app.run(debug=True, port=3001)
