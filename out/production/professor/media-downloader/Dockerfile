# This file is generated by Nx.
#
# Build the docker image with `npx nx docker-build media-downloader`.
# Tip: Modify "docker-build" options in project.json to change docker build args.
#
# Run the container with `docker run -p 3000:3000 -t media-downloader`.
FROM docker.io/node:lts-alpine

ENV HOST=0.0.0.0
ENV PORT=3000

WORKDIR /app

RUN addgroup --system media-downloader && \
          adduser --system -G media-downloader media-downloader

COPY dist/media-downloader media-downloader/
RUN chown -R media-downloader:media-downloader .

# You can remove this install step if you build with `--bundle` option.
# The bundled output will include external dependencies.
RUN npm --prefix media-downloader --omit=dev -f install

CMD [ "node", "media-downloader" ]
