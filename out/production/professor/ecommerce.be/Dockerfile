FROM node:18.17.0

RUN npm install -g pnpm
WORKDIR /app
RUN cd /app
COPY . .
RUN rm -rf node_modules/ pnpm-lock.json
RUN pnpm install

# RUN tsc 
RUN pnpm build
EXPOSE 3001
CMD ["pnpm", "start"]