const mongoose = require('mongoose')
const Schema = mongoose.Schema

const OrderSchema = mongoose.Schema({
    user_id: {
        type: Schema.Types.ObjectId,
        ref: 'User'
    },
    date: {
        type: Date,
        default: Date.now
    }

})

module.exports = mongoose.model('Order', OrderSchema)
