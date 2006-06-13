/*
 * Copyright (c) 2003-2006, Simon Brown
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in
 *     the documentation and/or other materials provided with the
 *     distribution.
 *
 *   - Neither the name of Pebble nor the names of its contributors may
 *     be used to endorse or promote products derived from this software
 *     without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.sourceforge.pebble.comment;

import net.sourceforge.pebble.domain.Comment;
import net.sourceforge.pebble.api.comment.AbstractCommentConfirmationStrategy;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * Simple maths comment confirmation strategy that asks the user to add two
 * random numbers together.
 *
 * @author    Simon Brown
 */
public class SimpleMathsCommentConfirmationStrategy extends AbstractCommentConfirmationStrategy {

  private static final String ARGUMENT1 = "SimpleMathsCommentConfirmationStrategyArg1";
  private static final String ARGUMENT2 = "SimpleMathsCommentConfirmationStrategyArg2";
  private static final String ANSWER = "SimpleMathsCommentConfirmationStrategyAnswer";

  /**
   * Called before showing the confirmation page.
   *
   * @param request the HttpServletRequest used in the confirmation
   * @param comment the Comment being confirmed
   */
  public void setupConfirmation(HttpServletRequest request, Comment comment) {
    Random r = new Random();
    int arg1 = r.nextInt(10) + 1;
    int arg2 = r.nextInt(10) + 1;
    request.getSession().setAttribute(ARGUMENT1, arg1);
    request.getSession().setAttribute(ARGUMENT2, arg2);
    request.getSession().setAttribute(ANSWER, arg1 + arg2);
  }

  /**
   * Gets the URI of the confirmation page.
   *
   * @return a URI, relative to the web application root.
   */
  public String getUri() {
    return "/WEB-INF/jsp/mathsCommentConfirmation.jsp";
  }

  /**
   * Called to confirm a comment.
   *
   * @param request the HttpServletRequest used in the confirmation
   * @param comment the Comment being confirmed
   * @return true if the comment has been successfully confirmed,
   *         false otherwise
   */
  public boolean confirmComment(HttpServletRequest request, Comment comment) {
    Integer answer = (Integer)request.getSession().getAttribute(ANSWER);
    String userAnswer = request.getParameter("answer");

    return answer.toString().equals(userAnswer);
  }

}