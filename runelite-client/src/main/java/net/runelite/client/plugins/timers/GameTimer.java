/*
 * Copyright (c) 2017, Seth <Sethtroll3@gmail.com>
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.timers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import javax.imageio.ImageIO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.GraphicID;

@Slf4j
public enum GameTimer
{
	STAMINA("stamina", 2, ChronoUnit.MINUTES),
	ANTIFIRE("antifire", 6, ChronoUnit.MINUTES),
	EXANTIFIRE("exantifire", 12, ChronoUnit.MINUTES),
	OVERLOAD("overload", 5, ChronoUnit.MINUTES),
	CANNON("cannon", 25, ChronoUnit.MINUTES),
	MAGICIMBUE("magicimbue", 15, ChronoUnit.SECONDS),
	FULLTB("teleblock", 5, ChronoUnit.MINUTES),
	HALFTB("teleblock", 150, ChronoUnit.SECONDS),
	SUPERANTIVENOM("antivenom", 3, ChronoUnit.MINUTES),
	SUPERANTIFIRE("superantifire", 2, ChronoUnit.MINUTES),
	ANTIDOTEPLUSPLUS("antidoteplusplus", 12, ChronoUnit.MINUTES),
	BIND("bind", GraphicID.BIND, 5, ChronoUnit.SECONDS),
	HALFBIND("bind", GraphicID.BIND, 2500, ChronoUnit.MILLIS),
	SNARE("snare", GraphicID.SNARE, 10, ChronoUnit.SECONDS),
	HALFSNARE("snare", GraphicID.SNARE, 5, ChronoUnit.SECONDS),
	ENTANGLE("entangle", GraphicID.ENTANGLE, 15, ChronoUnit.SECONDS),
	HALFENTANGLE("entangle", GraphicID.ENTANGLE, 7500, ChronoUnit.MILLIS),
	ICERUSH("icerush", GraphicID.ICE_RUSH, 5, ChronoUnit.SECONDS),
	ICEBURST("iceburst", GraphicID.ICE_BURST, 10, ChronoUnit.SECONDS),
	ICEBLITZ("iceblitz", GraphicID.ICE_BLITZ, 15, ChronoUnit.SECONDS),
	ICEBARRAGE("icebarrage", GraphicID.ICE_BARRAGE, 20, ChronoUnit.SECONDS),
	IMBUEDHEART("imbuedheart", GraphicID.IMBUED_HEART, 420, ChronoUnit.SECONDS),
	VENGEANCE("vengeance", GraphicID.VENGEANCE, 30, ChronoUnit.SECONDS);

	@Getter
	private final String imageResource;
	@Getter
	private final Duration duration;
	@Getter
	private final Integer graphicId;

	private BufferedImage image;

	GameTimer(String imageResource, Integer graphicId, long time, ChronoUnit unit)
	{
		this.imageResource = imageResource;
		this.graphicId = graphicId;
		this.duration = Duration.of(time, unit);
	}

	GameTimer(String imageResource, long time, ChronoUnit unit)
	{
		this(imageResource, null, time, unit);
	}

	public BufferedImage getImage()
	{
		if (image != null)
		{
			return image;
		}

		InputStream in = GameTimer.class.getResourceAsStream(imageResource + ".png");
		try
		{
			image = ImageIO.read(in);
		}
		catch (IOException ex)
		{
			log.warn("unable to load image", ex);
		}

		return image;
	}
}
