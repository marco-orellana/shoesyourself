const CartList = require('../models/CartList')

exports.SubmitProductInCart = async (req, res) => {
    const cartList = new CartList({
        cart_id: req.body.cart_id,
        product_id: req.body.product_id,
        quantity: req.body.quantity
    })

    try {
        const savedCartList = await cartList.save()
        res.json(savedCartList)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.GetCartListsById = async (req, res) => {
    try {
        const cartLists = await CartList.find({ cart_id: req.params.cartId })
        res.json(cartLists)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.DeleteProductInCart = async (req, res) => {
    try {
        const removeCartList = await CartList.deleteOne({ _id: req.params.cartListId })
        res.json(removeCartList)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.UpdateProductInCart = async (req, res) => {
    try {
        const updatedProduct = await CartList.updateMany({ _id: req.params.cartListId },
            {
                $set: {
                    quantity: req.body.quantity
                }
            })
        res.json(updatedProduct)
    } catch (err) {
        res.json({ message: err })
    }
}
