#!/usr/bin/env python
# coding=utf-8


class Const(object):

    def __setattr__(self, name, value):
        return


class USER_TYPE(Const):
    VIP = "vip"



if __name__ == "__main__":
    v = VIP()
    print v.VIP





