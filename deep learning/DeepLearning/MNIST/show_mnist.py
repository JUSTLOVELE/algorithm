import tensorflow as tf
from tensorflow import keras
from MNIST import  util
import numpy as np
import matplotlib.pyplot as plt

"""
也可以使用默认的数据包:
fashion_mnist = keras.datasets.fashion_mnist
(train_images, train_labels), (test_images, test_labels) = fashion_mnist.load_data()
"""
def show01(image):
    plt.figure()
    plt.imshow(image)
    plt.colorbar()
    plt.grid(False)
    plt.show()

def show25(train_images, train_labels):
    train_images = train_images / 255.0
    class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat',
                   'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']
    plt.figure(figsize=(10,10))
    for i in range(25):
        plt.subplot(5,5,i+1)
        plt.xticks([])
        plt.yticks([])
        plt.grid(False)
        plt.imshow(train_images[i], cmap=plt.cm.binary)
        plt.xlabel(class_names[train_labels[i]])
    plt.show()

(train_images, train_labels), (test_images, test_labels)  = util.mnist_load_data("/Users/yangzuliang/Documents/dataset/FashionMNIST/raw")
show01(train_images[0])
show25(train_images, train_labels)