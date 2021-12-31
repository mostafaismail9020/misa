jQuery(function() {
        var createParticlesSandbox, Utils;
        Utils = {},
            $.fn.jParticle = function(options) {
                return this.each(function(_, el) {
                    "object" == typeof el.sandbox && $(el).removeJParticle(),
                        el.sandbox = createParticlesSandbox(el, options)
                }),
                    this
            }
            ,
            $.fn.removeJParticle = function() {
                return this.each(function(_, el) {
                    el.sandbox && (el.sandbox.remove(),
                        delete el.sandbox)
                }),
                    this
            }
            ,
            $.fn.freezeJParticle = function() {
                return this.each(function(_, el) {
                    el.sandbox && el.sandbox.freeze()
                }),
                    this
            }
            ,
            $.fn.unfreezeJParticle = function() {
                return this.each(function(_, el) {
                    el.sandbox && el.sandbox.unfreeze()
                }),
                    this
            }
            ,
            createParticlesSandbox = function(element, params) {
                var ParticlesSandbox, createParticle;
                return ParticlesSandbox = {},
                    ParticlesSandbox.canvas = {},
                    ParticlesSandbox.mouse = {},
                    ParticlesSandbox.particles = [],
                    ParticlesSandbox.isAnimated = !1,
                    ParticlesSandbox.initialize = function(element, params) {
                        ParticlesSandbox.initParams(params),
                            ParticlesSandbox.initHTML(element),
                            ParticlesSandbox.initParticles(),
                            ParticlesSandbox.initEvents(),
                            ParticlesSandbox.initAnimation()
                    }
                    ,
                    ParticlesSandbox.initParams = function(params) {
                        params && params.color && (!params.particle || params.particle && !params.particle.color) && (params.particle || (params.particle = {}),
                            params.particle.color = params.color),
                            ParticlesSandbox.params = $.extend({
                                particlesNumber: 100,
                                linkDist: 50,
                                createLinkDist: 150,
                                disableLinks: !1,
                                disableMouse: !1,
                                background: "black",
                                color: "white",
                                width: null,
                                height: null,
                                linksWidth: 1
                            }, params)
                    }
                    ,
                    ParticlesSandbox.initHTML = function(element) {
                        var canvas;
                        canvas = ParticlesSandbox.canvas,
                            canvas.container = $(element),
                            canvas.element = $("<canvas/>"),
                            canvas.context = canvas.element.get(0).getContext("2d"),
                            canvas.container.append(canvas.element),
                            canvas.element.css("display", "block"),
                            canvas.element.get(0).width = ParticlesSandbox.params.width ? ParticlesSandbox.params.width : canvas.container.width(),
                            canvas.element.get(0).height = ParticlesSandbox.params.height ? ParticlesSandbox.params.height : canvas.container.height(),
                            canvas.element.css("background", ParticlesSandbox.params.background)
                    }
                    ,
                    ParticlesSandbox.resize = function(width, height) {
                        width && (canvas.element.get(0).width = width),
                        height && (canvas.element.get(0).height = height)
                    }
                    ,
                    ParticlesSandbox.initParticles = function() {
                        var i, count;
                        for (i = 0,
                                 count = ParticlesSandbox.params.particlesNumber; i < count; i += 1)
                            ParticlesSandbox.particles.push(createParticle(ParticlesSandbox.canvas.element.get(0), ParticlesSandbox.params.particle))
                    }
                    ,
                    ParticlesSandbox.initEvents = function() {
                        ParticlesSandbox.canvas.element.mouseenter(function() {
                            ParticlesSandbox.mouse.hoverCanvas = !0,
                            ParticlesSandbox.isAnimated || ParticlesSandbox.draw()
                        }),
                            ParticlesSandbox.canvas.element.mouseleave(function() {
                                ParticlesSandbox.mouse.hoverCanvas = !1
                            }),
                            ParticlesSandbox.canvas.element.mousemove(function(e) {
                                ParticlesSandbox.mouse = $.extend(ParticlesSandbox.mouse, Utils.getMousePosition(e, ParticlesSandbox.canvas.element[0]))
                            })
                    }
                    ,
                    ParticlesSandbox.initAnimation = function() {
                        window.requestAnimFrame = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.ORequestAnimationFrame || window.msRequestAnimationFrame || function(callback) {
                            setTimeOut(callback, 1e3 / 60)
                        }
                            ,
                            ParticlesSandbox.isAnimated = !0,
                            ParticlesSandbox.draw()
                    }
                    ,
                    ParticlesSandbox.draw = function() {
                        var i, j, count, canvas, particle, particle2;
                        for (i = 0,
                                 count = ParticlesSandbox.particles.length,
                                 canvas = ParticlesSandbox.canvas,
                                 canvas.context.clearRect(0, 0, canvas.element.get(0).width, canvas.element.get(0).height); i < count; i += 1)
                            if (particle = ParticlesSandbox.particles[i],
                            ParticlesSandbox.isAnimated && particle.update(),
                                particle.draw(),
                            !ParticlesSandbox.params.disableMouse && ParticlesSandbox.mouse.hoverCanvas && ParticlesSandbox.drawLink(particle.getPosition("x"), particle.getPosition("y"), ParticlesSandbox.mouse.x, ParticlesSandbox.mouse.y, ParticlesSandbox.params.mouseLinksColor),
                                !ParticlesSandbox.params.disableLinks)
                                for (j = i + 1; j < count; j += 1)
                                    particle2 = ParticlesSandbox.particles[j],
                                        ParticlesSandbox.drawLink(particle.getPosition("x"), particle.getPosition("y"), particle2.getPosition("x"), particle2.getPosition("y"));
                        ParticlesSandbox.requestID = window.requestAnimFrame(ParticlesSandbox.draw)
                    }
                    ,
                    ParticlesSandbox.drawLink = function(x, y, x2, y2, _color) {
                        var context;
                        Utils.getDistance(x, y, x2, y2) <= ParticlesSandbox.params.createLinkDist && (context = ParticlesSandbox.canvas.context,
                            context.save(),
                            context.beginPath(),
                            context.lineWidth = ParticlesSandbox.params.linksWidth,
                            context.moveTo(x, y),
                            context.lineTo(x2, y2),
                            context.globalAlpha = _color ? 1 : ParticlesSandbox.getOpacityLink(x, y, x2, y2),
                            context.strokeStyle = _color ? _color : ParticlesSandbox.params.color,
                            context.lineCap = "round",
                            context.stroke(),
                            context.closePath(),
                            context.restore())
                    }
                    ,
                    ParticlesSandbox.getOpacityLink = function(x, y, x2, y2) {
                        var dist, opacity, linkDist, createLinkDist;
                        return dist = Utils.getDistance(x, y, x2, y2),
                            linkDist = ParticlesSandbox.params.linkDist,
                            createLinkDist = ParticlesSandbox.params.createLinkDist,
                            opacity = dist <= linkDist ? 1 : dist > createLinkDist ? 0 : 1 - 100 * (dist - linkDist) / (createLinkDist - linkDist) / 100
                    }
                    ,
                    ParticlesSandbox.freeze = function() {
                        ParticlesSandbox.isAnimated && (ParticlesSandbox.isAnimated = !1)
                    }
                    ,
                    ParticlesSandbox.unfreeze = function() {
                        ParticlesSandbox.isAnimated || (ParticlesSandbox.isAnimated = !0)
                    }
                    ,
                    ParticlesSandbox.remove = function() {
                        ParticlesSandbox.canvas.element.remove()
                    }
                    ,
                    createParticle = function(canvas, params) {
                        var Particle;
                        return Particle = {},
                            Particle.canvas = {},
                            Particle.vector = {},
                            Particle.initialize = function(canvas, params) {
                                Particle.params = $.extend({
                                    color: "white",
                                    minSize: 2,
                                    maxSize: 4,
                                    speed: 60
                                }, params),
                                    Particle.setCanvasContext(canvas),
                                    Particle.initSize(),
                                    Particle.initPosition(),
                                    Particle.initVectors()
                            }
                            ,
                            Particle.initPosition = function() {
                                Particle.x = Utils.getRandNumber(0 + Particle.radius, Particle.canvas.element.width - Particle.radius),
                                    Particle.y = Utils.getRandNumber(0 + Particle.radius, Particle.canvas.element.height - Particle.radius)
                            }
                            ,
                            Particle.initSize = function() {
                                Particle.size = Utils.getRandNumber(Particle.params.minSize, Particle.params.maxSize),
                                    Particle.radius = Particle.size / 2
                            }
                            ,
                            Particle.initVectors = function() {
                                do
                                    Particle.vector.x = Utils.getRandNumber(-Particle.params.speed / 60, Particle.params.speed / 60, !1),
                                        Particle.vector.y = Utils.getRandNumber(-Particle.params.speed / 60, Particle.params.speed / 60, !1);
                                while (0 == Particle.vector.x || 0 == Particle.vector.y)
                            }
                            ,
                            Particle.setCanvasContext = function(canvas) {
                                var context;
                                if (Particle.canvas.element = canvas,
                                    context = canvas.getContext("2d"),
                                "object" != typeof context || "object" != typeof context.canvas)
                                    throw "Error: Can't set canvas context to Particle because context isn't a CanvasRenderingContext2D object.";
                                Particle.canvas.context = context
                            }
                            ,
                            Particle.draw = function() {
                                var context = Particle.canvas.context;
                                context.beginPath(),
                                    context.arc(Particle.x, Particle.y, Particle.size / 2, 0, 2 * Math.PI),
                                    context.fillStyle = Particle.params.color,
                                    context.fill(),
                                    context.closePath()
                            }
                            ,
                            Particle.update = function() {
                                Particle.x += Particle.vector.x,
                                    Particle.y += Particle.vector.y,
                                (0 > Particle.x - Particle.radius || Particle.x + Particle.radius > Particle.canvas.element.width) && (Particle.vector.x = -Particle.vector.x),
                                (0 > Particle.y - Particle.radius || Particle.y + Particle.radius > Particle.canvas.element.height) && (Particle.vector.y = -Particle.vector.y)
                            }
                            ,
                            Particle.getPosition = function(axis) {
                                return "string" == typeof axis && "x" != axis && "y" != axis && (axis = null),
                                    "string" == typeof axis ? Particle[axis] : {
                                        x: Particle.x,
                                        y: Particle.y
                                    }
                            }
                            ,
                            Particle.initialize(canvas, params),
                            {
                                getPosition: Particle.getPosition,
                                update: Particle.update,
                                draw: Particle.draw
                            }
                    }
                    ,
                    ParticlesSandbox.initialize(element, params),
                    {
                        remove: ParticlesSandbox.remove,
                        freeze: ParticlesSandbox.freeze,
                        unfreeze: ParticlesSandbox.unfreeze,
                        resize: ParticlesSandbox.resize
                    }
            }
            ,
            Utils.getRandNumber = function(x, y, round) {
                var value;
                return null == x && (x = 0),
                null == y && (y = 10),
                null == round && (round = !0),
                    value = Math.random() * (y - x) + x,
                    round ? Math.round(value) : value
            }
            ,
            Utils.getDistance = function(x, y, x2, y2) {
                return Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2))
            }
            ,
            Utils.getMousePosition = function(event, element) {
                var rect;
                return "undefined" == typeof element && (element = $("body")[0]),
                    rect = element.getBoundingClientRect(),
                    {
                        x: event.clientX - rect.left,
                        y: event.clientY - rect.top
                    }
            }
});


jQuery(function() {
    $(".particles-js").jParticle({
        // number of particles
        particlesNumber: 150,
        // Distance where link is full opacity
        linkDist: 30,
        // Distance where particles start linking.
        createLinkDist: 120,
        // disable links between particles
        // disable mouse interaction
        // background color
        background: 'transparent',
        // Particles and links color.
        color: 'rgba(255, 255, 255, 0.7)',
        mouseLinksColor: '#5cc83b',
    });
    $(".particles-js-2").jParticle({
        // number of particles
        particlesNumber: 150,
        // Distance where link is full opacity
        linkDist: 30,
        // Distance where particles start linking.
        createLinkDist: 120,
        // disable links between particles
        // disable mouse interaction
        // background color
        background: 'transparent',
        // Particles and links color.
        color: 'rgba(255, 255, 255, 0.7)',
        mouseLinksColor: '#5cc83b'
    });
});

