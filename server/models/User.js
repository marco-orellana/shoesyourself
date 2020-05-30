const mongoose = require('mongoose')
const Schema = mongoose.Schema

const UserSchema = mongoose.Schema({
    role_id: {
        type: Schema.Types.ObjectId,
        ref: 'Role'
    },
    email: {
        type: String,
        unique: true,
        required: true
    },
    first_name: {
        type: String,
        required: true
    },
    last_name: {
        type: String,
        required: true
    },
    img_url: {
        type: String,
        required: false
    },
    password: {
        type: String,
        required: true
    }
})

module.exports = mongoose.model('User', UserSchema)
