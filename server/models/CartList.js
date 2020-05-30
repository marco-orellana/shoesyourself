const mongoose = require('mongoose')
const Schema = mongoose.Schema

const CartListSchema = mongoose.Schema({
    cart_id: {
        type: Schema.Types.ObjectId,
        ref: 'Cart'
    },
    product_id: {
        type: Schema.Types.ObjectId,
        ref: 'Product'
    },
    quantity: {
        type: Number,
        default: 1
    }
})

module.exports = mongoose.model('CartList', CartListSchema)
