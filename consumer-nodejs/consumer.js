const amqp = require('amqplib')
const queue = 'PRICE'

amqp.connect({
  host:'localhost',
  port: 5672,
  username: 'admin',
  password: 123456
})
.then((conn) => {
  conn.createChannel()
    .then((channel) => {
      channel.consume(queue, (message) => {
        console.log(message.content.toString())
      }, { noAck: true })
    })
    .catch((error) => {
      console.log(error)
    })
})
.catch((error) => {
  console.log(error)
})