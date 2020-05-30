const mongoose = require('mongoose')
const Schema = mongoose.Schema

const ProductSchema = mongoose.Schema({
    brand_id: {
        type: Schema.Types.ObjectId,
        ref: 'Brand'
    },
    title: {
        type: String,
        required: true
    },
    description: {
        type: String,
        required: true
    },
    img_url: {
        type: String,
        required: true
    },
    price: {
        type: Number,
        required: true
    }
})

module.exports = mongoose.model('Product', ProductSchema)
