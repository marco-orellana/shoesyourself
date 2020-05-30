const express = require('express')
const app = express()
const bodyParser = require('body-parser')
const mongoose = require('mongoose')

app.use(bodyParser.json())

app.use(function (req, res, next) {
    res.header('Access-Control-Allow-Origin', '*')
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept')
    next()
})

// Import Routes
const userRoutes = require('./routes/user')
const productRoutes = require('./routes/product')
const roleRoutes = require('./routes/role')
// const categoryRoutes = require('./routes/category')
const brandRoutes = require('./routes/brand')
const cartRoutes = require('./routes/cart')

app.use('/users', userRoutes)
app.use('/products', productRoutes)
app.use('/roles', roleRoutes)
// app.use('/categories', categoryRoutes)
app.use('/brands', brandRoutes)
app.use('/cart', cartRoutes)

// MAIN ROUTES
app.get('/', (req, res) => {
    res.send('ShoesYourself.')
})

mongoose.set('useNewUrlParser', true)
mongoose.set('useFindAndModify', false)
mongoose.set('useCreateIndex', true)
mongoose.set('useUnifiedTopology', true)

// Connect to DB
mongoose.connect('mongodb+srv://admin:abc123...@shoesyourself-db-kfn1r.mongodb.net/test?retryWrites=true&w=majority',
    () => console.log('connected to DB!'))

// Connect to Server
const PORT = 8080
app.listen(PORT, function () {
    console.log('Server listening on: http://localhost:%s', PORT)
})
