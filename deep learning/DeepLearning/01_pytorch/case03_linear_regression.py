import torch
import torch.nn as nn

d_in = 3
d_out = 4
linear_module = nn.Linear(d_in, d_out)
example_tensor = torch.tensor([[1.,2,3], [4,5,6]])
# applys a linear transformation to the data
transformed = linear_module(example_tensor)
print('example_tensor', example_tensor.shape)
print('transormed', transformed.shape)
print()
print('We can see that the weights exist in the background\n')
print('W:', linear_module.weight)
print('b:', linear_module.bias)
#===========================ReLU====================================
activeation_fn = nn.ReLU()
example_tensor = torch.tensor([-1.0, 1.0, 1.0])
activated = activeation_fn(example_tensor)
print('activated', activated)
#===========================Sequential==============================
d_in = 3
d_hidden = 4
d_out = 1
model = torch.nn.Sequential(
    nn.Linear(d_in, d_hidden),
    nn.Tanh(),
    nn.Linear(d_hidden, d_out),
    nn.Sigmoid()
)
example_tensor = torch.tensor([[1.,2,3],[4,5,6]])
transformed = model(example_tensor)
print('tranformed', transformed.shape)
params = model.parameters()

for param in params:
    print(param)
#==========================Loss func============================
mse_loss_fn = nn.MSELoss()
input = torch.tensor([[0., 0, 0]])
target = torch.tensor([[1., 0, -1]])
loss = mse_loss_fn(input, target)
print(loss)
#===============================================================
model = nn.Linear(1, 1)
X_simple = torch.tensor([[1.]])
y_simple = torch.tensor([[2.]])
optim = torch.optim.SGD(model.parameters(), lr=0.001)
mse_loss_fn = nn.MSELoss()
y_hat = model(X_simple)
print('model params before:', model.weight)
loss = mse_loss_fn(y_hat, y_simple)
optim.zero_grad()
loss.backward()
optim.step()
print('model params after:', model.weight)
#===============================================================
# step_size = 0.1
# linear_module = nn.Linear(10, 1, bias=False)
# loss_func = nn.MSELoss()
# optim = torch.optim.SGD(linear_module.parameters(), lr=step_size)
# print('iter,\tloss,\tw')
#
# for i in range(20):
#     y_hat = linear_module(X)
#     loss = loss_func(y_hat, y)
#     optim.zero_grad()
#     loss.backward()
#     optim.step()
#     print('{},\t{:.2f},\t{}'.format(i, loss.item(), linear_module.weight.view(2).detach().numpy()))
#
# print('\ntrue w\t\t', true_w.view(2).numpy())
# print('estimated w\t', linear_module.weight.view(2).detach().numpy())
#================================Linear regression using SGD==============================================
#
# step_size = 0.01
#
# linear_module = nn.Linear(d, 1)
# loss_func = nn.MSELoss()
# optim = torch.optim.SGD(linear_module.parameters(), lr=step_size)
# print('iter,\tloss,\tw')
# for i in range(200):
#     rand_idx = np.random.choice(n)  # take a random point from the dataset
#     x = X[rand_idx]
#     y_hat = linear_module(x)
#     loss = loss_func(y_hat, y[rand_idx])  # only compute the loss on the single point
#     optim.zero_grad()
#     loss.backward()
#     optim.step()
#
#     if i % 20 == 0:
#         print('{},\t{:.2f},\t{}'.format(i, loss.item(), linear_module.weight.view(2).detach().numpy()))
#
# print('\ntrue w\t\t', true_w.view(2).numpy())
# print('estimated w\t', linear_module.weight.view(2).detach().numpy())