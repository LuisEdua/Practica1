from flask import Blueprint, request
import pika
from time import sleep
from threading import Thread
from src.Productos.Infrestructure.Controllers.ProductosControllers.GetController import GetController
from src.Productos.Infrestructure.Controllers.ProductosControllers.DeleteController import DeleteController
from src.Productos.Infrestructure.Controllers.ProductosControllers.CreateController import CreateController
from src.Productos.Infrestructure.Controllers.ProductosControllers.UpdateCantidadByEventController import UpdateCantidadByEventController
from src.Productos.Infrestructure.Repositories.MySQLRepositories.ProductosRepository import ProductosRepository

product_routes = Blueprint('product_routes', __name__)

repo = ProductosRepository()
get_controller = GetController(repo)
delete_controller = DeleteController(repo)
create_controller = CreateController(repo)
updater = UpdateCantidadByEventController(repo)

def callback_queue_change_orden_status(ch, method, properties, body):
    try:
        updater.run(body)
    except Exception as e:
        print(f"Error processing message: {e}")


def start_consuming_queue_change_orden_status():
    while True:
        try:
            connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
            channel = connection.channel()
            channel.queue_declare(queue='queue.change_orden_status', durable=True)
            channel.basic_consume(queue='queue.change_orden_status', on_message_callback=callback_queue_change_orden_status, auto_ack=True)
            channel.start_consuming()
        except pika.exceptions.AMQPConnectionError as e:
            print(f"Connection error: {e}, retrying in 5 seconds...")
            sleep(5)
        except Exception as e:
            print(f"Unexpected error: {e}")
            sleep(5)


def start_consumer_queue_change_orden_status_thread():
    consumer_thread = Thread(target=start_consuming_queue_change_orden_status)
    consumer_thread.daemon = True
    consumer_thread.start()


@product_routes.route("/", methods=["GET"])
def get():
    return get_controller.run()

@product_routes.route("/", methods=["POST"])
def create():
    return create_controller.run(request)

@product_routes.route("/<string:id>", methods=["DELETE"])
def delete(id):
    return delete_controller.run(id)
