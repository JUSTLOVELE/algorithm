import torch
from torch.autograd import Variable
"""
1.求梯度
2.detach、data、detach_
"""
#requires_grad=True:计算梯度
a = torch.tensor(2.0, requires_grad=True)
b = torch.tensor(1.0, requires_grad=True)
c = a + b
d = b + 1
e = c * d
print('c', c)
print('d', d)
print('e', e)
#--------------------------------------------------
def f(x):
    return (x-2)**2

def fp(x):
    return 2*(x-2)

x = torch.tensor([1.0], requires_grad=True)
y = f(x)
y.backward()
print('Analytical f\'(x):', fp(x))
print('PyTorch\'s f\'(x):', x.grad)
#---------------------------------------------------
x = torch.tensor([5.0], requires_grad=True)
step_size = 0.25
print('iter,\tx,\tf(x),\tf\'(x),\tf\'(x) pytorch')
for i in range(15):
    y = f(x)
    y.backward()#计算梯度

    print('{},\t{:.3f},\t{:.3f},\t{:.3f},\t{:.3f}'.format(i, x.item(), f(x).item(), fp(x).item(), x.grad.item()))
    # 执行一个GD update step
    x.data = x.data - step_size * x.grad
    #
    x.grad.detach_()
    #我们需要将grad变量归零，因为backward(),调用会在中累积梯度。梯度而不是覆盖。
    x.grad.zero_()

"""
detach:
    返回一个新的从当前图中分离的 Variable。
    返回的 Variable 永远不会需要梯度
    如果 被 detach 的Variable volatile=True， 那么 detach 出来的 volatile 也为 True
    还有一个注意事项，即：返回的 Variable 和 被 detach 的Variable 指向同一个 tensor
"""
print("==================detach==============")
t1 = torch.FloatTensor([1., 2.])
v1 = Variable(t1)
t2 = torch.FloatTensor([2., 3.])
v2 = Variable(t2)
v3 = v1 + v2
v3_detached = v3.detach()
v3_detached.data.add_(t1) # 修改了 v3_detached Variable中 tensor 的值
print(v3, v3_detached)    # v3 中tensor 的值也会改变
"""
detach_:
    将 Variable 从创建它的 graph 中分离，把它作为叶子节点。
    
     在x->y->z传播中，如果我们对y进行detach()，梯度还是能正常传播的，
     但如果我们对y进行detach_()，就把x->y->z切成两部分：x和y->z，x就无法接受到后面传过来的梯度
"""
print("==================detach_==============")
t1 = torch.FloatTensor([1., 2.])
v1 = Variable(t1)
t2 = torch.FloatTensor([2., 3.])
v2 = Variable(t2)
v3 = v1 + v2
v3_detached = v3.detach_()
v3_detached.data.add_(t1) # 修改了 v3_detached Variable中 tensor 的值
print(v3, v3_detached)    # v3 中tensor 的值也会改变
"""
detach和data:
    共同点：x.data（x.detach()） 返回和 x 的相同数据 tensor, 这个新的tensor和原来的tensor（即x）是共用数据的，
一者改变，另一者也会跟着改变，且require s_grad = False
    不同点： x.data 不能被 autograd 追踪求微分，即使被改了也能错误求导，而x.detach()则不行
"""
print("=================data==============")
a = torch.tensor([1, 2, 3.], requires_grad=True)
print(a.grad)
out = a.sigmoid()
print(out)
c = out.data
print(c)
c.zero_()  # 使用in place函数对其进行修改
# 会发现c的修改同时也会影响out的值
print(c)
print(out)
# 这里的不同在于.data的修改不会被autograd追踪，这样当进行backward()时它不会报错，回得到一个错误的backward值
out.sum().backward()
print(a.grad)