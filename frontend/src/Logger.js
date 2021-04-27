const winston = require('winston');

const logger = winston.createLogger({
    level: 'info',
    format: winston.format.json(),
});

export default logger