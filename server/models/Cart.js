const mongoose = require('mongoose')
const Schema = mongoose.Schema

const CartSchema = mongoose.Schema({
    user_id: {
        type: Schema.Types.ObjectId,
        ref: 'User'
    }

})

module.exports = mongoose.model('Cart', CartSchema)
