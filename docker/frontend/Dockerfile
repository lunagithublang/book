FROM node:20 as builder

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build --prod

FROM nginx:alpine

COPY nginx.conf /etc/nginx/nginx.conf

COPY --from=builder /usr/src/app/dist/frontend/browser /usr/share/nginx/html

EXPOSE 81 443
