const User = require('../models/User')
const Cart = require('../models/Cart')
const Order = require('../models/Order')
const bcrypt = require('bcryptjs')

exports.getAll = async (req, res) => {
    try {
        const users = await User.find()
        res.json(users)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.SubmitUser = async (req, res) => {
    const hashPassword = await bcrypt.hash(req.body.password, 12)
    const user = new User({
        role_id: req.body.role_id,
        email: req.body.email,
        first_name: req.body.first_name,
        last_name: req.body.last_name,
        img_url: req.body.img_url,
        password: req.body.password
    })

    try {
        const savedUser = await user.save()
        const cart = new Cart({
            user_id: savedUser._id
        })
        await cart.save()
        res.json(savedUser)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.GetUserById = async (req, res) => {
    try {
        const user = await User.findById(req.params.userId)
        res.json(user)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.GetCartById = async (req, res) => {
    try {
        const cart = await Cart.find({ user_id: req.params.userId })
        res.json(cart)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.GetOrderById = async (req, res) => {
    try {
        const order = await Order.find({ user_id: req.params.userId })
        res.json(cart)
    } catch (err) {
        res.json({ message: err })
    }
}
